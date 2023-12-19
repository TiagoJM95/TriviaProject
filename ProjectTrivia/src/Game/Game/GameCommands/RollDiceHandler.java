package Game.Game.GameCommands;


import Game.Game.Game;
import Game.Game.Messages.Messages;
import ServerClient.Server.Server;

public class RollDiceHandler implements GameCommandHandler {
    @Override
    public void execute(Game game, Server.ClientHandler player) {

        if(player.isMyTurn()){
            int diceRoll = game.getDice().rollDice();
            game.lobbyBroadcast(game.getDice().drawDice(diceRoll));
            game.lobbyBroadcast(player.getName() + Messages.ROLL_DICE+diceRoll);
            game.getBOARD().movePiece(diceRoll, player.getPiece());
        }
    }
}