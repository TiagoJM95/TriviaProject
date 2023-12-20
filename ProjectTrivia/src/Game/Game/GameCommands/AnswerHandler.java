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
            checkIfScore(game, player);
            game.lobbyBroadcast("Correct Answer!");
            game.printTurnOwner();
            return;
        }

        game.lobbyBroadcast("Wrong answer!");
        game.changeTurns(player);
        game.printTurnOwner();
    }
    private void checkIfScore(Game game,Server.ClientHandler player){
        Board.Position currentPosition = game.getBOARD().findPositionByPiece(player.getPiece());
      int positionId = currentPosition.getPositionId();
      if(game.getBOARD().getPrizedPositions().contains(positionId)){
          player.getScore().updateScore(currentPosition.getQuestionType());
          if(player.getScore().isAllPoints()){
              game.setGameOver(true);
              game.lobbyBroadcast("game Over");
          }
      }
    }
}