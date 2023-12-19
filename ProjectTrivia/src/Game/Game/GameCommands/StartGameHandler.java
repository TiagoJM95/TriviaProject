package Game.Game.GameCommands;

import Game.Board.PlayerPieces.Pieces;
import Game.Game.Game;
import ServerClient.Server.Messages.Messages;
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
        for (int i = 0; i < playerList.size(); i++) {

            playerList.get(i).setPiece(Pieces.generatePiece());
            game.getBOARD().setPiece(playerList.get(i).getPiece());
            game.getBOARD().movePiece(1, playerList.get(i).getPiece());
            playerList.get(i).send("This is your game piece: " + playerList.get(i).getPiece());

        }
        game.lobbyBroadcast(game.getBOARD().drawBoard());
    }
}