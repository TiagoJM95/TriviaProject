package Game.Game.GameCommands;

import ServerClient.Server.Messages.Messages;
import ServerClient.Server.Server;
import Game.Game.Game;

public class LeaveLobbyHandler implements GameCommandHandler {
    @Override
    public void execute(Game game, Server.ClientHandler player) {
        if(!game.getServer().isPlayerInGameLobby(player)){
            player.send(Messages.NOT_IN_LOBBY);
            return;
        }
        game.removePlayer(player);
        player.setGameId(0);
        player.send(Messages.LEFT_LOBBY);
    }
}