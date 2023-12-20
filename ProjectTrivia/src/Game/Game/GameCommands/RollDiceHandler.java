package Game.Game.GameCommands;


import Game.Game.Game;
import Game.Game.Messages.Messages;
import Game.Questions.QuestionType;
import Game.Questions.Questions;
import ServerClient.Server.Server;

import java.util.Random;

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

        game.getBOARD().movePiece(diceRoll, player.getPiece());
        game.lobbyBroadcast(game.getBOARD().drawBoard());

        game.lobbyBroadcast("\n\n");

        askQuestions(game, player);

    }

    private static void askQuestions(Game game, Server.ClientHandler player) {
        QuestionType questionType = game.getBOARD().findPositionByPiece(player.getPiece()).getQuestionType();
        game.setCurrentQuestion(game.getQuestions().askQuestion(questionType));
        game.lobbyBroadcast("\n\n" + game.getCurrentQuestion());
        game.lobbyBroadcast("\n" + game.getQuestions().getChoices(questionType, game.getCurrentQuestion()));
    }
}