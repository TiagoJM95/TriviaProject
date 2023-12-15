package ServerClient.Server.Commands;

import ServerClient.Server.Server.Server;
import Game.Game.Game;

public class QuitTheGame implements CommandHandler {
    @Override
    public void execute(Server server, Server.ClientConnectionHandler playerHandler) {
        //server.remove(playerHandler);
        //server.broadcast(playerHandler.getName() + Messages.PLAYER_DISCONNECTED);
        //playerHandler.close();
    }
}
