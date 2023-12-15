package ServerClient.Server.Commands;

import ServerClient.Server.Messages.Messages;
import ServerClient.Server.Server.Server;

public class QuitTheGame implements CommandHandler {
    @Override
    public void execute(Server server, Server.PlayerHandler playerHandler) {
        server.remove(playerHandler);
        server.broadcast(playerHandler.getName() + Messages.PLAYER_DISCONNECTED);
        playerHandler.close();
    }
}
