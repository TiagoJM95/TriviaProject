package Game.Game.GameCommands;

import ServerClient.Server.Commands.CommandHandler;
import ServerClient.Server.Messages.Messages;
import ServerClient.Server.Server.Server;
import Game.Game.Game;

public class LeaveLobbyHandler implements CommandHandler {
    @Override
    public void execute(Server server, Server.ClientHandler clientHandler) {
        if(!server.isPlayerInGameLobby(clientHandler)){
            clientHandler.send(Messages.NOT_IN_LOBBY);
            return;
        }
        Game game = server.getGameById(clientHandler.getGameId());
        game.setNumOfPlayers(game.getNumOfPlayers()-1);
        clientHandler.setGameId(0);
        clientHandler.send(Messages.LEFT_LOBBY);
    }
}
