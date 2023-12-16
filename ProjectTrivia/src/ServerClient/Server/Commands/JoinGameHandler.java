package ServerClient.Server.Commands;

import Game.Game.Game;
import ServerClient.Server.Server.Server;

public class JoinGameHandler implements CommandHandler {
    @Override
    public void execute(Server server, Server.ClientConnectionHandler clientConnectionHandler) {
        String message = clientConnectionHandler.getMessage();
        int gameId = Integer.parseInt(message.substring(14));
        server.joinGame(clientConnectionHandler, gameId);
    }
}