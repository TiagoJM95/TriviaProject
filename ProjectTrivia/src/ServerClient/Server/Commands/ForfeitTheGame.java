package ServerClient.Server.Commands;

import ServerClient.Server.Server.Server;
import Game.Game.Game_old;

public class ForfeitTheGame implements CommandHandler {
    @Override
    public void execute(Server server, Game_old.PlayerHandler playerHandler) {
        // todo method para desistir do jogo
        //playerHandler.send(Messages.PLAYER_FORFEIT);
    }
}
