package Game.Game.GameCommands;

import Game.Game.Game;
import Game.Game.Messages.Messages;
import Game.Questions.QuestionType;
import ServerClient.Server.Server;

public class RollDiceHandler implements GameCommandHandler {
    @Override
    public void execute(Game game, Server.ClientHandler player) {

        if(!player.isMyTurn()){
            player.send(Messages.NOT_YOUR_TURN);
            return;
        }

        if(player.getMessage().length() > GameCommand.ROLL.getDescription().length()){
            player.send(Messages.INVALID_USE_OF_COMMAND);
            return;
        }

        int diceRoll = game.getDice().rollDice();
        game.lobbyBroadcast(player.getName() + Messages.ROLL_DICE+diceRoll);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        game.getBoard().movePiece(diceRoll, player.getPiece());
        game.lobbyBroadcast(game.getDice().drawDice(diceRoll));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        game.lobbyBroadcast(game.getBoard().drawBoard());
        game.lobbyBroadcast("\n");

        askQuestions(game, player);
    }

    private static void askQuestions(Game game, Server.ClientHandler player) {
        QuestionType questionType = game.getBoard().findPositionByPiece(player.getPiece()).getQuestionType();
        game.setCurrentQuestion(game.getQuestions().askQuestion(questionType));
        game.lobbyBroadcast("\n" + game.getCurrentQuestion());
        game.lobbyBroadcast("\n" + game.getQuestions().getChoices(questionType, game.getCurrentQuestion()));
    }
}