package Game.Game.GameCommands;


import Game.Board.Board;
import Game.Game.Game;
import Game.Game.Messages.Messages;
import Game.Questions.Questions;
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

        if(Questions.checkIfAnswerIsCorrect(game.getCurrentQuestion(), answer)){
            checkIfCanGetPoints(game, player);
            game.lobbyBroadcast(Messages.CORRECT_ANSWER);
            game.printTurnOwner();
            return;
        }

        game.lobbyBroadcast(Messages.WRONG_ANSWER);
        game.changeTurns(player);
        game.printTurnOwner();
    }

    private void checkIfCanGetPoints(Game game, Server.ClientHandler player){

        Board.Position currentPosition = game.getBOARD().findPositionByPiece(player.getPiece());
        int positionId = currentPosition.getPositionId();

        if(game.getBOARD().getPrizedPositions().contains(positionId)){
            player.getScore().updateScore(currentPosition.getQuestionType());
            checkIfGameCanEnd(game, player);
        }
    }

    private static void checkIfGameCanEnd(Game game, Server.ClientHandler player) {

        if(player.getScore().isAllPoints()){
          game.setGameOver(true);
          game.endGame(player);
        }
    }
}