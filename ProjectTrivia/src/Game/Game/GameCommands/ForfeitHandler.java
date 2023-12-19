package Game.Game.GameCommands;


import Game.Game.Game;
import ServerClient.Server.Server;

public class ForfeitHandler implements GameCommandHandler {

    @Override
    public void execute(Game game, Server.ClientHandler player) {
        game.removePlayer(player);
        player.setGameId(0);
        game.lobbyBroadcast(player.getName() + " has forfeit the game!");
    }
}
