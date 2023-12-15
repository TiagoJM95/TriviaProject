package ServerClient.Server.Commands;

import ServerClient.Server.Server.Server;
import Game.Game.Game_old;

@FunctionalInterface
public interface CommandHandler {
    void execute(Server server, Game_old.PlayerHandler playerHandler);
}
