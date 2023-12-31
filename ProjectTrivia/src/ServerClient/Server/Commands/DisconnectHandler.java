package ServerClient.Server.Commands;

import ServerClient.Server.Messages.Messages;
import ServerClient.Server.Server;

public class DisconnectHandler implements CommandHandler {
    @Override
    public void execute(Server server, Server.ClientHandler clientHandler) {

        if(clientHandler.getMessage().length() > Command.DISCONNECT.getDescription().length()){
            clientHandler.send(Messages.INVALID_USE_OF_COMMAND);
            return;
        }
        server.broadcast(clientHandler,clientHandler.getName() + Messages.PLAYER_DISCONNECTED);
        server.removeClient(clientHandler);
        clientHandler.close();
    }
}