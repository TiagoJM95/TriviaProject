package ServerClient.Server.Commands;

import ServerClient.Server.Server.Server;

public class ListPlayersHandler implements CommandHandler {
    @Override
    public void execute(Server server, Server.ClientHandler clientHandler) {
        clientHandler.send(server.listClients());
    }
}
