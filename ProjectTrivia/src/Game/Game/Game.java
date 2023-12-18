package Game.Game;

import Game.Board.Board;
import Game.Dice.Dice;
import Game.Game.GameCommands.GameCommand;
import Game.Questions.QuestionType;
import ServerClient.Server.Server.Server;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Game {
    private final int GAME_ID;
    public static final int MAX_PLAYERS = 3;
    private final Server SERVER;
    private Dice dice;
    private final List<Server.ClientHandler> PLAYERS;
    private final Board board;
    private QuestionType questionsType;


    public Game(int gameCounter, Server server){
        this.GAME_ID = gameCounter;
        this.SERVER = server;
        this.PLAYERS = new LinkedList<>();
        this.board = new Board();
        this.dice = new Dice();
    }

    public void addPlayer(Server.ClientHandler player){
        PLAYERS.add(player);
    }

    public void removePlayer(Server.ClientHandler player){
        PLAYERS.remove(player);
    }

    public Server getServer(){
        return SERVER;
    }

    public int getGameId(){
        return GAME_ID;
    }

    public boolean isGameFull(){
        return MAX_PLAYERS == PLAYERS.size();
    }

    public int getNumOfPlayers(){
        return PLAYERS.size();
    }

    public void lobbyBroadcast(String message){
        PLAYERS.forEach(handler -> handler.send(message));
    }

    public void dealWithCommand(String message, Server.ClientHandler player) {
        String description = message.split(" ")[0];
        GameCommand command = GameCommand.getCommand(description);

        command.getHandler().execute(this, player);
    }

    public void startGame(){
        lobbyBroadcast(board.drawBoard());
    }

    public String getCategoriesType() {
        return Arrays.toString(QuestionType.values());
    }

    public Dice getDice() {
        return dice;
    }
}