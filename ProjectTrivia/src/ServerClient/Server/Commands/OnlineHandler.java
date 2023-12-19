package ServerClient.Server.Commands;

import ServerClient.Server.Messages.Messages;
import ServerClient.Server.Server;

public class OnlineHandler implements CommandHandler {
    @Override
    public void execute(Server server, Server.ClientHandler clientHandler) {
        if(clientHandler.getMessage().length() > Command.ONLINE.getDescription().length()){
            clientHandler.send(Messages.INVALID_USE_OF_COMMAND);
            return;
        }
        clientHandler.send(server.getClients().stream()
                .filter(handler -> !handler.equals(clientHandler))
                .map(Server.ClientHandler::getName)
                .toList()
                .toString());
    }
}
