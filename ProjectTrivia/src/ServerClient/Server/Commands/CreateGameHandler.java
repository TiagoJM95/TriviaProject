package ServerClient.Server.Commands;

import ServerClient.Server.Messages.Messages;
import ServerClient.Server.Server.Server;

public class CreateGameHandler implements CommandHandler{
    @Override
    public void execute(Server server, Server.ClientHandler clientHandler) {
        if(server.isPlayerInGameLobby(clientHandler)){
            clientHandler.send(Messages.ALREADY_IN_LOBBY);
            return;
        }
        server.createGame(clientHandler);
    }
}