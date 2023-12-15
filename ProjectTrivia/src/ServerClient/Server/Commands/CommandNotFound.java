package ServerClient.Server.Commands;

import ServerClient.Server.Messages.Messages;
import ServerClient.Server.Server.Server;

public class CommandNotFound implements CommandHandler {
    @Override
    public void execute(Server server, Server.PlayerHandler playerHandler) {
        playerHandler.send(Messages.NO_SUCH_COMMAND);
    }
}
