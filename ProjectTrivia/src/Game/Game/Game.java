package Game.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ServerClient.Server.Server.Server;
import Game.Board.Board;
import Game.Dice.Dice;
import Game.Questions.Questions;
import Game.Score.Score;

public class Game implements Runnable{

    private final Server server;
    private final Set<PlayerHandler> playerList;
    private final ExecutorService gameService;
    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 6;
    private final boolean gameStarted;
    private final boolean gameEnded;
    private final Board board;
    private final Dice dice;
    private final Set<Questions> questionsList;
    private final Score score;


    public Game(Server server){
        this.server = server;
        this.gameService = Executors.newFixedThreadPool(MAX_PLAYERS);
        this.playerList = new HashSet<>();
        this.gameStarted = false;
        this.gameEnded = false;
        this.board = new Board();
        this.dice = new Dice();
        this.questionsList = new HashSet<>();
        this.score = new Score();
    }


    @Override
    public void run() {
        while(!gameEnded){

            if(checkIfGameCanStart()){
                System.out.println("StartGame!");
                startGame();
            }

            if(gameStarted){
                playGame();
                endGame();
            }
        }
    }

    private void playGame() {
    }

    public boolean isAcceptingPlayers(){
        return false;
    }

    public void acceptPlayer(Socket playerSocket){
        gameService.submit(new PlayerHandler(playerSocket));
        System.out.println("Player Accepted");
    }

    private boolean checkIfGameCanStart(){
        if(gameStarted){
          return false;
        }
        return false;
    }

    private void startGame(){

    }

    private void playTurn(){

    }

    private void drawBoard(){

    }

    private void checkIfGameCanEnd(){

    }

    private void endGame(){

    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public class PlayerHandler implements Runnable{

        private String name;
        private final Socket playerSocket;
        private final BufferedReader reader;
        private final PrintWriter writer;
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

        @Override
        public void run() {
            addPlayerToGame(this);
            try {
                defineUsername();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if(gameEnded){
                try {
                    quit();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        private void quit() throws IOException {
            playerSocket.close();
        }

        private void addPlayerToGame(PlayerHandler playerHandler) {


            if (playerList.contains(playerHandler)){
                System.out.println("Player already on list");
                return;
            }

            playerList.add(playerHandler);
        }

        private void defineUsername() throws IOException {
            sendMessage("Define username: ");
            this.name = reader.readLine();
        }

        private void sendMessage(String message){
            writer.println(message);
        }

    }
}