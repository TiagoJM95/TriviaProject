package Game.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ServerClient.Server.Commands.Command;
import ServerClient.Server.Server.Server;
import Game.Board.Board;
import Game.Dice.Dice;
import Game.Questions.Questions;
import Game.Score.Score;

public class Game implements Runnable{

    private final Server server;
    private final Set<PlayerHandler> playerList;
    private final ExecutorService gameService;
    private final int minPlayers = 2;
    private final int maxPlayers = 6;
    private boolean gameStarted;
    private boolean gameEnded;
    private final Board board;
    private final Dice dice;
    private final List<Questions> questionsList;
    private final Score score;


    public Game(Server server){
        this.server = server;
        this.gameService = Executors.newFixedThreadPool(maxPlayers);
        this.playerList = new HashSet<>();
        this.gameStarted = false;
        this.gameEnded = false;
        this.board = new Board();
        this.dice = new Dice();
        this.questionsList = new ArrayList<>();
        this.score = new Score();
    }


    @Override
    public void run() {
        while(!isGameEnded()){

            if(checkIfGameCanStart()){
                startGame();
            }

            if(isGameStarted()){
                try {
                    playGame();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            endGame();
        }
    }
    private boolean checkIfGameCanStart(){
        return playerList.size() >= minPlayers && !isAcceptingPlayers();
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    private void playGame() throws IOException {
        for(PlayerHandler player : playerList){

            if(player.inGame){

                broadcast(player.username + "'s turn");
                String playerMessage = player.readMessage();

                if (player.isCommand(playerMessage)) {
                    player.executeCommand(playerMessage);
                }
            }
            if(isGameEnded()){
                return;
            }
        }
    }

    public boolean isAcceptingPlayers(){
        return playerList.size() < maxPlayers && !gameStarted;
    }

    public void acceptPlayer(Socket playerSocket){
        gameService.submit(new PlayerHandler(playerSocket));
    }

    private void startGame(){
        gameStarted = true;
        broadcast("Game has started");
        broadcast(drawBoard());
    }

    private void broadcast(String message){
        playerList.stream()
                .filter(player -> player.inGame)
                .forEach(player -> player.sendMessage(message));
    }

    private void playTurn(){

    }

    private String drawBoard(){
        return "Board";
    }

    private void checkIfGameCanEnd(){

    }

    private void endGame(){
        gameEnded = true;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public class PlayerHandler implements Runnable{

        private final Socket playerSocket;
        private final BufferedReader reader;
        private final PrintWriter writer;
        private String username;
        private String message;
        private boolean inGame;

        public PlayerHandler(Socket playerSocket){
            this.playerSocket = playerSocket;
            try {
                reader = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));
                writer = new PrintWriter(playerSocket.getOutputStream(),true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void sendMessage(String message){
            writer.println(message);
        }

        private String readMessage() throws IOException {
            return reader.readLine();
        }

        @Override
        public void run() {

            try {
                addPlayerToGame(this);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            while(!isGameEnded()){
                try {
                    message = reader.readLine();
                    if(isCommand(message)){
                        executeCommand(message);
                        continue;
                    }
                    sendMessage("Invalid input");

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                quit();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void executeCommand(String message) {
        }

        private boolean isCommand(String message) {
            return message.startsWith("/");
        }

        private void quit() throws IOException {
            playerSocket.close();
        }

        private void addPlayerToGame(PlayerHandler playerHandler) throws IOException {


            if (playerList.contains(playerHandler)){
                System.out.println("Player already on list");
                return;
            }

            playerList.add(playerHandler);
            defineUsername();
        }

        private void defineUsername() throws IOException {
            sendMessage("Define username: ");
            this.username = reader.readLine();
        }

    }
}