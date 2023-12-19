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

        if(player.isMyTurn()){
            int diceRoll = game.getDice().rollDice();
            game.lobbyBroadcast(game.getDice().drawDice(diceRoll));
            game.lobbyBroadcast(player.getName() + Messages.ROLL_DICE+diceRoll);
            game.getBOARD().movePiece(diceRoll, player.getPiece());
            game.lobbyBroadcast(game.getBOARD().drawBoard());
            //questions
            int rand = new Random().nextInt(Questions.getQuestionList().size());
            game.setCurrentQuestion(Questions.getQuestionList().get(rand));
            game.lobbyBroadcast(game.getCurrentQuestion());
            return;
        }
        player.send(Messages.NOT_YOUR_TURN);
    }
}