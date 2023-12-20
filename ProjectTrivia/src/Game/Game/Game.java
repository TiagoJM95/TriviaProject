package Game.Game;

import Game.Board.Board;
import Game.Dice.Dice;
import Game.Game.GameCommands.GameCommand;
import Game.Game.Messages.Messages;
import Game.Questions.Questions;
import ServerClient.Server.Server;
import java.util.*;

public class Game {
    public static final int REQUIRED_PLAYERS = 3;
    private final Dice dice;
    private final int gameId;
    private final Board board;
    private boolean gameStarted;
    private final Server server;
    private String currentQuestion;
    private final Questions questions;
    private final List<Server.ClientHandler> playerList;


    public Game(int gameCounter, Server server){
        this.server = server;
        this.gameStarted = false;
        this.gameId = gameCounter;
        this.playerList = new ArrayList<>();
        this.board = new Board(this);
        this.dice = new Dice();
        this.questions = new Questions();
    }


    public void addPlayer(Server.ClientHandler player){
        playerList.add(player);
    }

    public void removePlayer(Server.ClientHandler player){
        playerList.remove(player);
    }

    public void lobbyBroadcast(Server.ClientHandler clientHandler, String message){
        playerList.stream()
                .filter(handler -> !handler.equals(clientHandler))
                .forEach(handler -> handler.send(message));
    }

    public void lobbyBroadcast(String message){
        playerList.forEach(handler -> handler.send(message));
    }

    public void dealWithCommand(String message, Server.ClientHandler player) {
        String description = message.split(" ")[0];
        GameCommand command = GameCommand.getCommand(description);

        command.getHandler().execute(this, player);
    }

    public void start(){
        for (int i = 0; i < 3; i++) {
            lobbyBroadcast(Messages.GAME_BEGINS + (3-i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        lobbyBroadcast("\n\n"+Messages.COMMAND_LIST_GAME);
    }

    public void endGame(Server.ClientHandler player){

        lobbyBroadcast(player.getName() + Messages.WIN_MESSAGE);

        for (Server.ClientHandler p: playerList){
            p.setGameId(0);
            p.setMyTurn(false);
            p.setPiece("");
            playerList.remove(p);
        }
        getServer().endGame(this);
    }

    public void changeTurns(Server.ClientHandler player){
        for (Server.ClientHandler p : playerList){
            p.setMyTurn(false);
        }
        if(playerList.indexOf(player)+1< playerList.size()){
            playerList.get(playerList.indexOf(player)+1).setMyTurn(true);
            return;
        }
        playerList.getFirst().setMyTurn(true);
    }

    public void printTurnOwner() {
        for(Server.ClientHandler p: playerList){
            if(p.isMyTurn()){
                lobbyBroadcast(String.format(Messages.PLAYER_TURN, p.getName()));
            }
        }
    }

    public Dice getDice() {
        return dice;
    }

    public int getGameId(){
        return gameId;
    }

    public Board getBoard() {
        return board;
    }

    public Server getServer(){
        return server;
    }

    public Questions getQuestions() {
        return questions;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public int getNumOfPlayers(){
        return playerList.size();
    }

    public String getCurrentQuestion() {
        return currentQuestion;
    }

    public List<Server.ClientHandler> getPlayers() {
        return playerList;
    }

    public boolean isGameFull(){
        return REQUIRED_PLAYERS == playerList.size();
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public void setCurrentQuestion(String currentQuestion) {
        this.currentQuestion = currentQuestion;
    }
}