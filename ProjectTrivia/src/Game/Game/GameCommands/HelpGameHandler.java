package Game.Game.GameCommands;

import Game.Game.Game;
import Game.Game.Messages.Messages;
import ServerClient.Server.Server;

public class HelpGameHandler implements GameCommandHandler {
    @Override
    public void execute(Game game, Server.ClientHandler player) {
        player.send(Messages.COMMAND_LIST_GAME);
    }
}
