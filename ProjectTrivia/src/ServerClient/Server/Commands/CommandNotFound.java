package ServerClient.Server.Commands;

import ServerClient.Server.Server.Server;

public class CommandNotFound implements CommandHandler {
    @Override
    public void execute(Server server, Server.ClientConnectionHandler playerHandler) {
        //playerHandler.send(Messages.NO_SUCH_COMMAND);
    }
}
