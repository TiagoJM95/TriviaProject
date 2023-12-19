package Game.Game.GameCommands;


import Game.Game.Game;
import Game.Game.Messages.Messages;
import ServerClient.Server.Server;

public class ForfeitHandler implements GameCommandHandler {

    @Override
    public void execute(Game game, Server.ClientHandler player) {

        if(player.getMessage().length() > GameCommand.FORFEIT.getDescription().length()){
            player.send(Messages.INVALID_USE_OF_COMMAND);
            return;
        }
        game.removePlayer(player);
        player.setGameId(0);
        game.lobbyBroadcast(player.getName() + " has forfeit the game!");
    }
}
