package Game.Game.GameCommands;


import Game.Game.Game;
import Game.Game.Messages.Messages;
import ServerClient.Server.Server;

public class ForfeitHandler implements GameCommandHandler {

    @Override
    public void execute(Game game, Server.ClientHandler player) {
        // todo method para desistir do jogo
        player.send(Messages.PLAYER_FORFEIT);
    }
}
