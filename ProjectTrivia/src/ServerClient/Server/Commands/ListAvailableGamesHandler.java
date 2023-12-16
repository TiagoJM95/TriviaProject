package ServerClient.Server.Commands;

import Game.Game.Game;
import ServerClient.Server.Server.Server;

public class ListAvailableGamesHandler implements CommandHandler {
    @Override
    public void execute(Server server, Server.ClientConnectionHandler clientConnectionHandler) {
        clientConnectionHandler.send(server.listGames());
    }
}