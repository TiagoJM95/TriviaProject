package Game.Game.GameCommands;


import Game.Game.Game;
import ServerClient.Server.Server.Server;

public class ForfeitHandler implements GameCommandHandler {

    @Override
    public void execute(Game game, Server.ClientHandler player) {
        // todo method para desistir do jogo
        //playerHandler.send(Messages.PLAYER_FORFEIT);
    }
}
