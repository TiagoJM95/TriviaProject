package ServerClient.Server.Commands;

import ServerClient.Server.Server;

public class OnlineHandler implements CommandHandler {
    @Override
    public void execute(Server server, Server.ClientHandler clientHandler) {
        clientHandler.send(server.getClients().stream()
                .filter(handler -> !handler.equals(clientHandler))
                .map(Server.ClientHandler::getName)
                .toList()
                .toString());
    }
}
