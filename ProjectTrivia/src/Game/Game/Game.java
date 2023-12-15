package Game.Game;

import ServerClient.Server.Server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Game implements Runnable{
    private final Server SERVER;
    private final int minPlayers = 2;
    private final int maxPlayers = 6;
    private final List<Handler> playersList;
    private final ExecutorService service;
    private boolean gameStarted;
    private boolean gameEnded;

    public Game(Server server){
        this.SERVER = server;
        service = Executors.newFixedThreadPool(maxPlayers);
        playersList = new ArrayList<>();
        gameStarted = false;
        gameEnded = false;
    }
    @Override
    public void run(){
        while(!gameEnded){

            if(checkIfGameCanStart() && !gameStarted){
                startGame();
            }
            if(gameStarted && !gameEnded){
                playTurn();
            }
        }
        endGame();
    }

    public boolean isAcceptingPlayers(){
        return playersList.size() < maxPlayers && !gameStarted;
    }

    public void acceptPlayer(Socket socket){
        service.submit(new Handler(socket));
    }

    private void addPlayerToList(Handler handler){
        playersList.add(handler);
        handler.send("Welcome");
    }

    private boolean checkIfGameCanStart(){
        return !isAcceptingPlayers() || playersList.size() >= minPlayers;
        //Add time condition;
    }

    private void startGame(){
        gameStarted = true;
        broadcast("Game Started");
        broadcast(drawBoard());
    }

    private void playTurn(){
        for(Handler handler : playersList){
            if(!handler.hasLeft()){

                broadcast("Handlers name turn");
                handler.send("/your turn");
            }
        }
    }

    private String drawBoard(){
        return "Board";
    }

    public void broadcast(String message){
        playersList.forEach(player -> player.send(message));
    }

    public void endGame(){
        broadcast("Game Ended");
        playersList.forEach(Handler::quit);
        gameEnded = true;
    }

    public class Handler implements Runnable{
        private String name;
        private final Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private boolean hasLeft;


        public Handler(Socket socket){
            this.socket = socket;

            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                quit();
            }
        }

        @Override
        public void run(){
            addPlayerToList(this);

            send("Whats your name?");
            name = receive();

            broadcast(name + "has joined");
            send("Waiting for other players");

            while(!gameEnded){
                if(Thread.interrupted()){
                    return;
                }
            }
            quit();
        }

        public String receive(){
            String message = null;
            try {
                message = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return message;
        }

        public void send(String message){
            out.println(message);
        }

        public boolean hasLeft(){
            return hasLeft;
        }

        public void quit(){
            hasLeft = true;

            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println("Player has left!");
            }
        }

    }
}
