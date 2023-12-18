package Game.Game.GameCommands;


import Game.Game.Game;
import Game.Game.Messages.Messages;
import ServerClient.Server.Server.Server;

public class RollDiceHandler implements GameCommandHandler {
    @Override
    public void execute(Game game, Server.ClientHandler player) {
        //todo still need to add method here
        player.send(Messages.ROLL_DICE);
    }
}