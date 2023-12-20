package Game.Game;

import Game.Board.Board;
import Game.Dice.Dice;
import Game.Game.GameCommands.GameCommand;
import Game.Questions.QuestionType;
import ServerClient.Server.Server;

import java.util.*;

public class Game {
    private final Dice dice;
    private String currentQuestion;
    private final Board BOARD;
    private final int GAME_ID;
    private final Server SERVER;
    private boolean gameStarted;
    private boolean gameOver;
    public static final int MAX_PLAYERS = 3;
    private final List<Server.ClientHandler> PLAYERS;



    public Game(int gameCounter, Server server){
        this.SERVER = server;
        this.gameStarted = false;
        this.GAME_ID = gameCounter;
        this.PLAYERS = new ArrayList<>();
        this.BOARD = new Board();
        this.dice = new Dice();
    }

    public void addPlayer(Server.ClientHandler player){
        PLAYERS.add(player);
    }

    public void removePlayer(Server.ClientHandler player){
        PLAYERS.remove(player);
    }

    public void lobbyBroadcast(Server.ClientHandler clientHandler, String message){
        PLAYERS.stream()
                .filter(handler -> !handler.equals(clientHandler))
                .forEach(handler -> handler.send(message));
    }

    public void lobbyBroadcast(String message){
        PLAYERS.forEach(handler -> handler.send(message));
    }

    public void dealWithCommand(String message, Server.ClientHandler player) {
        String description = message.split(" ")[0];
        GameCommand command = GameCommand.getCommand(description);

        command.getHandler().execute(this, player);
    }

    public void start(){
        for (int i = 0; i < 3; i++) {
            lobbyBroadcast("\nGame is starting in: " + (3-i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        lobbyBroadcast("\n\n");
    }

    public void changeTurns(Server.ClientHandler player){
        for (Server.ClientHandler p : PLAYERS){
            p.setMyTurn(false);
        }
        if(PLAYERS.indexOf(player)+1<PLAYERS.size()){
            PLAYERS.get(PLAYERS.indexOf(player)+1).setMyTurn(true);
            return;
        }
        PLAYERS.getFirst().setMyTurn(true);
    }

    public void printTurnOwner() {
        for(Server.ClientHandler p: PLAYERS){
            if(p.isMyTurn()){
                lobbyBroadcast("It's " + p.getName() + "'s turn!");
            }
        }
    }

    private boolean isThereAGameWinner() {
        return false;
    }

    public Dice getDice() {
        return dice;
    }

    public Board getBOARD() {
        return BOARD;
    }

    public int getGameId(){
        return GAME_ID;
    }

    public Server getServer(){
        return SERVER;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public int getNumOfPlayers(){
        return PLAYERS.size();
    }

    public String getCurrentQuestion() {
        return currentQuestion;
    }

    public List<Server.ClientHandler> getPLAYERS() {
        return PLAYERS;
    }

    public boolean isGameFull(){
        return MAX_PLAYERS == PLAYERS.size();
    }

    public String getCategoriesType() {
        return Arrays.toString(QuestionType.values());
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public void setCurrentQuestion(String currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}