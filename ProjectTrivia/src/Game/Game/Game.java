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

    private Server server;
    private Set<PlayerHandler> playerList;
    private ExecutorService gameService;
    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 6;
    private boolean gameStarted;
    private boolean gameEnded;
    private Board board;
    private Dice dice;
    private Set<Questions> questionsList;
    private Score score;


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
                startGame();
            }

            if(gameStarted){
                playGame();
                endGame();
            }
        }
    }

    public boolean isAcceptingPlayers(){
        return false;
    }

    public void acceptPlayer(Socket playerSocket){

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
        private Socket playerSocket;
        private BufferedReader reader;
        private PrintWriter writer;
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

    }
}