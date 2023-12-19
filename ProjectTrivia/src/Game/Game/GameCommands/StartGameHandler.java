package Game.Game.GameCommands;

import Game.Board.PlayerPieces.Pieces;
import Game.Game.Game;
import Game.Game.Messages.Messages;
import ServerClient.Server.Server;

import java.util.List;

public class StartGameHandler implements GameCommandHandler{

    @Override
    public void execute(Game game, Server.ClientHandler player) {
        List<Server.ClientHandler> playerList = game.getPLAYERS();

        if(game.isGameStarted()){
            player.send(Messages.NO_SUCH_COMMAND);
            return;
        }
        game.setGameStarted(true);
        for (Server.ClientHandler clientHandler : playerList) {

            clientHandler.setPiece(Pieces.generatePiece());
            game.getBOARD().setPiece(clientHandler.getPiece());
            clientHandler.send(Messages.GAME_PIECES + clientHandler.getPiece());

        }
        game.lobbyBroadcast(game.getBOARD().drawBoard());
        game.playTurn();
    }
}