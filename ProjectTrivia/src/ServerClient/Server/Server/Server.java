package ServerClient.Server.Server;

import ServerClient.Server.Commands.Command;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import ServerClient.Server.Messages.Messages;
import Game.Game.Game;

public class Server {

    private int numOfConnections;
    private final List<Game> games;
    private ExecutorService service;
    private ServerSocket serverSocket;
    private static int gameCounter = 0;
    private final List<ClientHandler> clients;

    public Server() {
        this.numOfConnections = 0;
        this.games = new CopyOnWriteArrayList<>();
        this.clients = new CopyOnWriteArrayList<>();
    }

    public void start(int port){

        try {
            serverSocket = new ServerSocket(port);
            service = Executors.newCachedThreadPool();
            System.out.printf(Messages.SERVER_STARTED, port);

            while(serverSocket.isBound()){
                acceptConnection(numOfConnections++);
            }

        } catch (IOException e) {
            System.out.println(Messages.SERVER_ERROR_1);
        }
    }

    private void acceptConnection(int num) throws IOException {
        Socket clientSocket = serverSocket.accept();
        ClientHandler clientHandler = new ClientHandler(clientSocket, Messages.DEFAULT_PLAYER_NAME + num);
        service.submit(clientHandler);
    }

    private void addClient(ClientHandler clientHandler){
        clients.add(clientHandler);
        clientHandler.send(Messages.GAME_BANNER);
        clientHandler.send(Messages.WELCOME + clientHandler.getName());
        clientHandler.send(Messages.COMMAND_LIST_SERVER);
        broadcast(clientHandler, clientHandler.getName() + Messages.HAS_CONNECTED);
    }

    private void removeClient(ClientHandler clientHandler){
        clients.remove(clientHandler);
    }

    public void broadcast(ClientHandler clientHandler, String message){
        clients.stream()
                .filter(handler -> !handler.equals(clientHandler))
                .forEach(handler -> handler.send(message));
    }

    public void lobbyBroadcast(ClientHandler clientHandler, String message){
        clients.stream()
                .filter(handler -> !handler.equals(clientHandler))
                .filter(handler -> handler.gameId == clientHandler.gameId)
                .forEach(handler -> handler.send(message));
    }

    public Optional<ClientHandler> getClientByName(String name){
        return clients.stream()
                .filter(client -> client.getName().equals(name))
                .findFirst();
    }

    public String listClients(){
        String names = "";

        for(ClientHandler client : clients){
            names = names + "\n" + client.getName();
        }

        return names;
    }

    public void createGame(ClientHandler clientHandler){
        games.add(new Game(++gameCounter, this));
        addPlayerToGame(clientHandler, gameCounter);
        clientHandler.send(Messages.GAME_CREATED);
        broadcast(clientHandler, Messages.ALL_GAME_CREATED+gameCounter);
    }

    public String listGames(){
        String gameList = Messages.GAME_LIST;

        for(Game game : games){
            gameList = gameList + "Game "+game.getGameId()+" ➜ "+game.getNumOfPlayers()+"/"+Game.MAX_PLAYERS;
        }
        if(gameList.length()==Messages.GAME_LIST.length()){
            gameList = Messages.NO_GAME_LOBBY;
        }
        return gameList;
    }

    private void addPlayerToGame(ClientHandler clientHandler, int gameId) {
        Game game = getGameById(gameId);

        if(game!=null){
            game.addPlayer(clientHandler);
            clientHandler.gameId = gameId;
            clientHandler.send(Messages.JOIN_LOBBY+gameId);
            lobbyBroadcast(clientHandler, clientHandler.getName()+Messages.PLAYER_JOINED_LOBBY);
        }
    }

    public void joinGame(ClientHandler clientHandler, int gameId){
       Game game = getGameById(gameId);

       if(game==null){
           clientHandler.send(Messages.NO_SUCH_GAME);
           return;
       }
       if(game.isGameFull()){
           clientHandler.send(Messages.FULL_LOBBY);
           return;
       }
        addPlayerToGame(clientHandler, gameId);
    }

    public void disconnectClient(ClientHandler clientHandler){
        broadcast(clientHandler,clientHandler.getName() + Messages.PLAYER_DISCONNECTED);
        removeClient(clientHandler);
        clientHandler.close();
    }

    public boolean isPlayerInGameLobby(ClientHandler clientHandler) {
        return clientHandler.gameId != 0;
    }

    public Game getGameById(int gameId) {
        for (Game game : games){
            if(game.getGameId()==gameId){
                return game;
            }
        }
        return null;
    }



    public class ClientHandler implements Runnable {

        private int gameId;
        private String name;
        private String message;
        private boolean myTurn;
        private String piece;
        private final BufferedWriter out;
        private final Socket clientSocket;

        public ClientHandler(Socket clientSocket, String name) throws IOException {
            this.name = name;
            this.myTurn = false;
            this.clientSocket = clientSocket;
            this.out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        }

        @Override
        public void run() {

            addClient(this);
            Scanner in = null;

            try {
                in = new Scanner(clientSocket.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            while(in.hasNext()){
                message = in.nextLine();

                if(isCommand(message)){
                    if(isPlayerInGameLobby(this)){
                        getGameById(this.gameId).dealWithCommand(message, this);
                        continue;
                    }
                    dealWithCommand(message);
                }
            }
        }

        private boolean isCommand(String message){
            return message.startsWith("/");
        }

        private void dealWithCommand(String message){
            String description = message.split(" ")[0];
            Command command = Command.getCommand(description);

            command.getHandler().execute(Server.this, this);
        }

        public void send(String message){
            try {
                out.write(message);
                out.newLine();
                out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void close(){
            try {
                clientSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public int getGameId() {
            return gameId;
        }

        public String getName() {
            return name;
        }

        public boolean isMyTurn() {
            return myTurn;
        }

        public String getMessage() {
            return message;
        }

        public void setGameId(int gameId) {
            this.gameId = gameId;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setMyTurn(boolean myTurn) {
            this.myTurn = myTurn;

        }

        public String getPiece() {
            return piece;
        }

        public void setPiece(String piece) {
            this.piece = piece;
        }
    }
}