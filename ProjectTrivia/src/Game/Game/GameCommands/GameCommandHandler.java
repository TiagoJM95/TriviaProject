package Game.Game.GameCommands;

import Game.Game.Game;
import ServerClient.Server.Server.Server;

@FunctionalInterface
public interface GameCommandHandler {
    void execute(Game game, Server.ClientHandler player);
}
