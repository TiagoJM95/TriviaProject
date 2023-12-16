package ServerClient.Server.Commands;

import ServerClient.Server.Server.Server;

public class DisconnectHandler implements CommandHandler {
    @Override
    public void execute(Server server, Server.ClientHandler clientHandler) {
        server.disconnectClient(clientHandler);
    }
}