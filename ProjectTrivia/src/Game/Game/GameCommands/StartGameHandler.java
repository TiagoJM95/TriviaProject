package Game.Game.GameCommands;

import Game.Game.Game;
import ServerClient.Server.Messages.Messages;
import ServerClient.Server.Server;

public class StartGameHandler implements GameCommandHandler{

    @Override
    public void execute(Game game, Server.ClientHandler player) {
        if(!game.getServer().isPlayerInGameLobby(player)){
            player.send(Messages.NOT_IN_LOBBY);
            return;
        }
        game.startGame();
        game.lobbyBroadcast("Game started!");
    }
}