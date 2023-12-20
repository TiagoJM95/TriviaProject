package Game.Game.GameCommands;

import Game.Board.Board;
import Game.Game.Game;
import Game.Game.Messages.Messages;
import Game.Questions.QuestionType;
import ServerClient.Server.Server;

public class AnswerHandler implements GameCommandHandler {
    @Override
    public void execute(Game game, Server.ClientHandler player) {
        if(!player.isMyTurn()){
            player.send(Messages.NOT_YOUR_TURN);
            return;
        }
        if(!player.getMessage().substring(8).matches("[ABCD]")){
            player.send(Messages.INVALID_USE_OF_COMMAND);
            return;
        }

        String answer = player.getMessage().substring(8);
        QuestionType questionType = game.getBoard().findPositionByPiece(player.getPiece()).getQuestionType();

        game.lobbyBroadcast(String.format(Messages.PLAYER_ANSWER, player.getName(), answer));

        if(game.getQuestions().checkIfAnswerIsCorrect(questionType,game.getCurrentQuestion(), answer)){
            validateCorrectAnswer(game, player, questionType);
            return;
        }

        game.lobbyBroadcast(Messages.WRONG_ANSWER);
        game.changeTurns(player);
        game.printTurnOwner();
    }

    private void checkIfCanGetPoints(Game game, Server.ClientHandler player){

        Board.Position currentPosition = game.getBoard().findPositionByPiece(player.getPiece());
        int positionId = currentPosition.getPositionId();
        QuestionType questionType = currentPosition.getQuestionType();

        if(game.getBoard().getPrizedPositions().contains(positionId)){
            player.getScore().updateScore(questionType);
            game.getBoard().markScoreCategory(questionType, game.getPlayers().indexOf(player));
            checkIfGameCanEnd(game, player);
        }
    }

    private static void checkIfGameCanEnd(Game game, Server.ClientHandler player) {

        if(player.getScore().isAllPoints()){
            game.endGame(player);
        }
    }

    private void validateCorrectAnswer(Game game, Server.ClientHandler player, QuestionType questionType) {
        game.getQuestions().removeQuestion(questionType, game.getCurrentQuestion());
        checkIfCanGetPoints(game, player);
        game.lobbyBroadcast(Messages.CORRECT_ANSWER);
        game.printTurnOwner();
    }
}