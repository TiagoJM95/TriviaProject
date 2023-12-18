package Game.Game.GameCommands;


import Game.Game.Game;
import Game.Game.Messages.Messages;
import ServerClient.Server.Server;

public class CategoriesHandler implements GameCommandHandler {

    @Override
    public void execute(Game game, Server.ClientHandler player) {
        player.send(Messages.QUESTIONS_CATEGORIES + game.getCategoriesType());
    }
}
