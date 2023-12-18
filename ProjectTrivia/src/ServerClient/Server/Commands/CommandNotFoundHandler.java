package ServerClient.Server.Commands;

import ServerClient.Server.Messages.Messages;
import ServerClient.Server.Server;

public class CommandNotFoundHandler implements CommandHandler {
    @Override
    public void execute(Server server, Server.ClientHandler clientHandler) {
        clientHandler.send(Messages.NO_SUCH_COMMAND);
    }
}