package ServerClient.Server.Commands;

import Game.Game.Game;
import ServerClient.Server.Server.Server;

public class ListPlayers implements CommandHandler {
    @Override
    public void execute(Server server, Server.ClientConnectionHandler playerHandler) {
        //  playerHandler.send(server.listPlayers()); todo adicionar method no server
    }
}
