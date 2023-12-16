package Game.Game;

public class Game {
    private final int GAME_ID;
    public static final int MAX_PLAYERS = 3;
    private int numOfPlayers = 0;

    public Game(int gameCounter){
        this.GAME_ID = gameCounter;
    }

    public int getGameId(){
        return GAME_ID;
    }

    public boolean isGameFull(){
        return MAX_PLAYERS == numOfPlayers;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public void dealWithCommand(String message) {
        String description = message.split(" ")[0];
        //GameCommand command = GameCommand.getCommand(description);

        //command.getHandler().execute(Server.this, this);
    }

    public void startGame(){

    }
}