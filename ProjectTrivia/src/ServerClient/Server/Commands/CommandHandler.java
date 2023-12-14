package ServerClient.Server.Commands;

import ServerClient.Server.Server.Server;
import Game.Game.Game;

@FunctionalInterface
public interface CommandHandler {
    void execute(Server server, Game.PlayerHandler playerHandler);
}
