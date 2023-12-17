package Game.Game;

import Game.Board.Board;
import Game.Game.GameCommands.GameCommand;
import ServerClient.Server.Server.Server;
import java.util.LinkedList;
import java.util.List;

public class Game {
    private final int GAME_ID;
    public static final int MAX_PLAYERS = 3;
    private final Server SERVER;
    private final List<Server.ClientHandler> PLAYERS;
    private Board board;


    public Game(int gameCounter, Server server){
        this.GAME_ID = gameCounter;
        this.SERVER = server;
        this.PLAYERS = new LinkedList<>();
        this.board = new Board();
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

    public void dealWithCommand(String message, Server.ClientHandler player) {
        String description = message.split(" ")[0];
        GameCommand command = GameCommand.getCommand(description);

        command.getHandler().execute(this, player);
    }

    public void startGame(){
        PLAYERS.forEach(player -> player.send(board.drawBoard()));
    }
}