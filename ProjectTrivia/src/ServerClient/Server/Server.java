package ServerClient.Server;

import static ServerClient.Server.Messages.Messages.*;
import Game.Score.Score;
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
            System.out.printf(SERVER_STARTED, port);

            while(serverSocket.isBound()){
                acceptConnection(numOfConnections++);
            }

        } catch (IOException e) {
            System.out.println(SERVER_ERROR_1);
        }
    }

    private void acceptConnection(int num) throws IOException {
        Socket clientSocket = serverSocket.accept();
        ClientHandler clientHandler = new ClientHandler(clientSocket, DEFAULT_PLAYER_NAME + num);
        service.submit(clientHandler);
    }

    private void addClient(ClientHandler clientHandler){
        clients.add(clientHandler);
        clientHandler.send(GAME_BANNER + WELCOME + clientHandler.getName() + COMMAND_LIST_SERVER);
        broadcast(clientHandler, clientHandler.getName() + HAS_CONNECTED);
    }

    public void removeClient(ClientHandler clientHandler){
        clients.remove(clientHandler);
    }

    public void broadcast(ClientHandler clientHandler, String message){
        clients.stream()
                .filter(handler -> !handler.equals(clientHandler))
                .filter(handler -> handler.gameId==0)
                .forEach(handler -> handler.send(message));
    }

    public void addPlayerToGame(ClientHandler clientHandler, int gameId) {
        Game game = getGameById(gameId);

        if(game!=null){
            game.addPlayer(clientHandler);
            clientHandler.gameId = gameId;
        }
    }

    public boolean isPlayerInGameLobby(ClientHandler clientHandler) {
        return clientHandler.gameId != 0;
    }

    public void endGame(Game game) {
        games.remove(game);
    }

    public Game getGameById(int gameId) {
        for (Game game : games){
            if(game.getGameId()==gameId){
                return game;
            }
        }
        return null;
    }

    public List<Game> getGames() {
        return games;
    }

    public static int getGameCounter() {
        return gameCounter;
    }

    public List<ClientHandler> getClients() {
        return clients;
    }

    public Optional<ClientHandler> getClientByName(String name){
        return clients.stream()
                .filter(client -> client.getName().equals(name))
                .findFirst();
    }

    public static void setGameCounter(int gameCounter) {
        Server.gameCounter = gameCounter;
    }


    public class ClientHandler implements Runnable {

        private int gameId;
        private String name;
        private String message;
        private boolean myTurn;
        private String piece;
        private final BufferedWriter out;
        private final Socket clientSocket;
        private final Score score;

        public ClientHandler(Socket clientSocket, String name) throws IOException {
            this.score = new Score();
            this.name = name;
            this.myTurn = false;
            this.clientSocket = clientSocket;
            this.out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        }

        @Override
        public void run() {

            addClient(this);
            Scanner in;

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

        public String getName() {
            return name;
        }

        public Score getScore() {
            return score;
        }

        public String getPiece() {
            return piece;
        }

        public boolean isMyTurn() {
            return myTurn;
        }

        public String getMessage() {
            return message;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPiece(String piece) {
            this.piece = piece;
        }

        public void setGameId(int gameId) {
            this.gameId = gameId;
        }

        public void setMyTurn(boolean myTurn) {
            this.myTurn = myTurn;
        }
    }
}