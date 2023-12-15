package ServerClient.Server.Commands;

import ServerClient.Server.Server.Server;
import Game.Game.Game_old;

public class ListPlayers implements CommandHandler {
    @Override
    public void execute(Server server, Game_old.PlayerHandler playerHandler) {
        //  playerHandler.send(server.listPlayers()); todo adicionar method no server
    }
}
