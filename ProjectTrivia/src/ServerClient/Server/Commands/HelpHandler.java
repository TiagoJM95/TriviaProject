package ServerClient.Server.Commands;

import ServerClient.Server.Messages.Messages;
import ServerClient.Server.Server;

public class HelpHandler implements CommandHandler {
    @Override
    public void execute(Server server, Server.ClientHandler clientHandler) {

        if(clientHandler.getMessage().length() > Command.CREATE_GAME.getDescription().length()){
            clientHandler.send(Messages.INVALID_USE_OF_COMMAND);
            return;
        }
        clientHandler.send(Messages.COMMAND_LIST_SERVER);
    }
}