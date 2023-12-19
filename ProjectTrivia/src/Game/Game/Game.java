package Game.Game;

import Game.Board.Board;
import Game.Dice.Dice;
import Game.Game.GameCommands.GameCommand;
import Game.Questions.QuestionType;
import ServerClient.Server.Server;

import java.util.*;

public class Game {
    private final Dice dice;
    private final Board BOARD;
    private final int GAME_ID;
    private final Server SERVER;
    private boolean gameStarted;
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

    public int getGameId(){
        return GAME_ID;
    }

    public Server getServer(){
        return SERVER;
    }

    public int getNumOfPlayers(){
        return PLAYERS.size();
    }

    public boolean isGameFull(){
        return MAX_PLAYERS == PLAYERS.size();
    }

    public String getCategoriesType() {
        return Arrays.toString(QuestionType.values());
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public Board getBOARD() {
        return BOARD;
    }

    public List<Server.ClientHandler> getPLAYERS() {
        return PLAYERS;
    }

    public void changeTurns(int playerIndex){
        for (Server.ClientHandler player : PLAYERS){
            player.setMyTurn(false);
        }
        PLAYERS.get(playerIndex).setMyTurn(true);
    }

    public void playTurn(){
        while(!isThereAGameWinner()){
            for(Server.ClientHandler player : PLAYERS){
                if (player.isMyTurn()){

                    lobbyBroadcast(player.getName() + "'s turn! Roll your dice!");

                }
            }
        }
    }

    private boolean isThereAGameWinner() {
        for (Server.ClientHandler p: PLAYERS){
            if(p.getScore() == 6){
                return true;
            }
        }
        return false;
    }

    public Dice getDice() {
        return dice;
    }
}