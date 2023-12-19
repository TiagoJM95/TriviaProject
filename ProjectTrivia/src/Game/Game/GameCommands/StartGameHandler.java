package Game.Game.GameCommands;

import Game.Board.PlayerPieces.Pieces;
import Game.Game.Game;
import ServerClient.Server.Messages.Messages;
import ServerClient.Server.Server;

public class StartGameHandler implements GameCommandHandler{

    @Override
    public void execute(Game game, Server.ClientHandler player) {
        if(game.isGameStarted()){
            player.send(Messages.NO_SUCH_COMMAND);
            return;
        }
        game.setGameStarted(true);
        for (int i = 0; i < game.getPLAYERS().size(); i++) {
            game.getPLAYERS().get(i).setPiece(Pieces.generatePiece());
            game.getBOARD().movePiece(1, game.getPLAYERS().get(i).getPiece());
            game.getPLAYERS().get(i).send("This is your game piece: " + game.getPLAYERS().get(i).getPiece());
        }
        game.lobbyBroadcast(game.getBOARD().drawBoard());
    }
}