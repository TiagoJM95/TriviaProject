package ServerClient.Server.Commands;

import ServerClient.Server.Messages.Messages;
import ServerClient.Server.Server.Server;
import Game.Game.Game;

public class ForfeitTheGame implements CommandHandler {
    @Override
    public void execute(Server server, Game.PlayerHandler playerHandler) {
        // todo method para desistir do jogo
        //playerHandler.send(Messages.PLAYER_FORFEIT);
    }
}
