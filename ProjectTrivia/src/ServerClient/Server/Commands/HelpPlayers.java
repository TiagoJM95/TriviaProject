package ServerClient.Server.Commands;

import ServerClient.Server.Messages.Messages;
import ServerClient.Server.Server.Server;
import Game.Game.Game;

public class HelpPlayers implements CommandHandler {
    @Override
    public void execute(Server server, Game.PlayerHandler playerHandler) {
        //playerHandler.send(Messages.COMMAND_LIST);
    }
}
