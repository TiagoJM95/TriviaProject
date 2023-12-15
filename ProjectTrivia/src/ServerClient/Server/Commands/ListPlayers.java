package ServerClient.Server.Commands;

import ServerClient.Server.Server.Server;

public class ListPlayers implements CommandHandler {
    @Override
    public void execute(Server server, Server.PlayerHandler playerHandler) {
        //  playerHandler.send(server.listPlayers()); todo adicionar method no server
    }
}
