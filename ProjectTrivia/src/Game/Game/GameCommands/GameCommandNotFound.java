package Game.Game.GameCommands;

import ServerClient.Server.Commands.CommandHandler;
import ServerClient.Server.Server.Server;

public class GameCommandNotFound implements GameCommandHandler {
    @Override
    public void execute() {
        //playerHandler.send(Messages.NO_SUCH_COMMAND);
    }
}
