package ServerClient.Server.Commands;

import ServerClient.Server.Server.Server;
import Game.Game.Game_old;

public class CommandNotFound implements CommandHandler {
    @Override
    public void execute(Server server, Game_old.PlayerHandler playerHandler) {
        //playerHandler.send(Messages.NO_SUCH_COMMAND);
    }
}
