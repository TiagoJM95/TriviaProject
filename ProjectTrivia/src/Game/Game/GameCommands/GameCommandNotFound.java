package Game.Game.GameCommands;

import Game.Game.Game;
import ServerClient.Server.Messages.Messages;
import ServerClient.Server.Server;

public class GameCommandNotFound implements GameCommandHandler {

    @Override
    public void execute(Game game, Server.ClientHandler player) {
        player.send(Messages.NO_SUCH_COMMAND);
    }
}
