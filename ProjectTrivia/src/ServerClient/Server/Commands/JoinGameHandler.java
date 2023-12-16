package ServerClient.Server.Commands;

import ServerClient.Server.Messages.Messages;
import ServerClient.Server.Server.Server;

public class JoinGameHandler implements CommandHandler {
    @Override
    public void execute(Server server, Server.ClientHandler clientHandler) {
        if(server.isPlayerInGameLobby(clientHandler)){
            clientHandler.send(Messages.ALREADY_IN_LOBBY);
            return;
        }
        String message = clientHandler.getMessage();
        int gameId = Integer.parseInt(message.substring(10));
        server.joinGame(clientHandler, gameId);
    }
}