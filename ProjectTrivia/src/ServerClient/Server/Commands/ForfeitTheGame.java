package ServerClient.Server.Commands;

import Game.Game.Game;
import ServerClient.Server.Server.Server;

public class ForfeitTheGame implements CommandHandler {
    @Override
    public void execute(Server server, Server.ClientConnectionHandler playerHandler) {
        // todo method para desistir do jogo
        //playerHandler.send(Messages.PLAYER_FORFEIT);
    }
}
