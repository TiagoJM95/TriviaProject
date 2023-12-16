package Game.Game;

import ServerClient.Server.Server.Server;

public class Game {
    private final int GAME_ID;

    public Game(int gameCounter){
        this.GAME_ID = gameCounter;
    }

    public int getGameId(){
        return GAME_ID;
    }

    public void dealWithCommand(String message) {
        String description = message.split(" ")[0];
        //GameCommand command = GameCommand.getCommand(description);

        //command.getHandler().execute(Server.this, this);
    }
}