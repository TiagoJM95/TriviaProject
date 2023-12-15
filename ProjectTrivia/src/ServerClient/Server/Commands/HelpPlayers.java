package ServerClient.Server.Commands;

import ServerClient.Server.Server.Server;
import Game.Game.Game_old;

public class HelpPlayers implements CommandHandler {
    @Override
    public void execute(Server server, Game_old.PlayerHandler playerHandler) {
        //playerHandler.send(Messages.COMMAND_LIST);
    }
}
