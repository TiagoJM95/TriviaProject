package ServerClient.Server.Commands;

import ServerClient.Server.Messages.Messages;
import ServerClient.Server.Server;

public class DisconnectHandler implements CommandHandler {
    @Override
    public void execute(Server server, Server.ClientHandler clientHandler) {
        server.broadcast(clientHandler,clientHandler.getName() + Messages.PLAYER_DISCONNECTED);
        server.removeClient(clientHandler);
        clientHandler.close();
    }
}