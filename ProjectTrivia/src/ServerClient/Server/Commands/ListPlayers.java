package ServerClient.Server.Commands;

import ServerClient.Server.Server.Server;
import Game.Game.Game;

public class ListPlayers implements CommandHandler {
    @Override
    public void execute(Server server, Game.PlayerHandler playerHandler) {
        //  playerHandler.send(server.listPlayers()); todo adicionar method no server
    }
}
