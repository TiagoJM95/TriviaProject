package Game.Game.GameCommands;

import ServerClient.Server.Commands.CommandHandler;
import ServerClient.Server.Messages.Messages;
import ServerClient.Server.Server.Server;

public class StartGameHandler implements CommandHandler {
    @Override
    public void execute(Server server, Server.ClientHandler clientHandler) {
        if(!server.isPlayerInGameLobby(clientHandler)){
            clientHandler.send(Messages.NOT_IN_LOBBY);
            return;
        }
        server.getGameById(clientHandler.getGameId()).startGame();
        //Broadcast to lobby game start;
    }
}