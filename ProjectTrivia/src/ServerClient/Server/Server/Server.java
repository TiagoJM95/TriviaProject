package ServerClient.Server.Server;

import ServerClient.Server.Commands.Command;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import ServerClient.Server.Messages.Messages;
import Game.Game.Game;

public class Server {

    private ServerSocket serverSocket;
    private ExecutorService service;
    private final List<ClientConnectionHandler> clients;
    private int numOfConnections;
    private final List<Game> games;
    private static int gameCounter = 0;

    public Server() {
        this.clients = new CopyOnWriteArrayList<>();
        this.numOfConnections = 0;
        this.games = new ArrayList<>();
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
            throw new RuntimeException(e);
        }
    }

    private void acceptConnection(int num) throws IOException {
        Socket clientSocket = serverSocket.accept();
        ClientConnectionHandler clientConnectionHandler = new ClientConnectionHandler(clientSocket, Messages.DEFAULT_PLAYER_NAME + num);
        service.submit(clientConnectionHandler);
    }

    private void addClient(ClientConnectionHandler clientConnectionHandler){
        clients.add(clientConnectionHandler);
        clientConnectionHandler.send(Messages.WELCOME + clientConnectionHandler.getName());
        clientConnectionHandler.send(Messages.COMMAND_LIST_SERVER);
        broadcast(clientConnectionHandler.getName() + Messages.HAS_CONNECTED);
    }

    private void removeClient(ClientConnectionHandler clientConnectionHandler){
        clients.remove(clientConnectionHandler);
    }

    public void broadcast(String message){
        clients.forEach(handler -> handler.send(message));
    }

    public Optional<ClientConnectionHandler> getClientByName(String name){
        return clients.stream()
                .filter(client -> client.getName().equals(name))
                .findFirst();
    }

    public String listClients(){
        String names = "";

        for(ClientConnectionHandler client : clients){
            names = names + "\n" + client.getName();
        }

        return names;
    }

    public void createGame(ClientConnectionHandler clientConnectionHandler){
        games.add(new Game(gameCounter));
        addPlayerToGame(clientConnectionHandler, gameCounter);
        gameCounter++;
        broadcast(Messages.GAME_CREATED);
    }

    public String listGames(){
        String gameList = "";

        for(Game game : games){
            gameList = gameList + "\nGame" + game.getGameId() + " :  " + game.getNumOfPlayers() + " players in lobby!";
        }

        if(gameList.isEmpty()){
            gameList = "No Game lobbies created!";
        }

        return gameList;
    }

    private void addPlayerToGame(ClientConnectionHandler clientConnectionHandler, int gameId) {

        Game game = getGameById(gameId);

        if(game!=null){
            clientConnectionHandler.gameId = gameId;
            game.setNumOfPlayers(game.getNumOfPlayers()+1);
            clientConnectionHandler.send("You have joined a lobby");
        }

    }

    public void joinGame(ClientConnectionHandler clientConnectionHandler, int gameId){
       Game game = getGameById(gameId);

       if(game == null){
           clientConnectionHandler.send("There is no game with such ID!");
           return;
       }
       if(!game.isGameFull()){
           addPlayerToGame(clientConnectionHandler, gameId);
       }
    }

    public void disconnectClient(ClientConnectionHandler clientConnectionHandler){
        broadcast(clientConnectionHandler.getName() + "has disconnected from the server!");
        removeClient(clientConnectionHandler);
        clientConnectionHandler.close();
    }

    private boolean isPlayerInGameLobby(ClientConnectionHandler clientConnectionHandler) {
        return clientConnectionHandler.gameId != 0;
    }

    private Game getGameById(int gameId) {

        for (Game game : games){

            if(game.getGameId()==gameId){
                return game;
            }
        }
        return null;
    }


    public class ClientConnectionHandler implements Runnable {

        private String name;
        private final Socket clientSocket;
        private final BufferedWriter out;
        private String message;
        private int gameId;

        public ClientConnectionHandler(Socket clientSocket, String name) throws IOException {
            this.clientSocket = clientSocket;
            this.name = name;
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

                if(isPlayerInGameLobby(this) && isCommand(message)){
                    getGameById(this.gameId).dealWithCommand(message);
                }

                if(isCommand(message)){
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMessage() {
            return message;
        }
    }
}