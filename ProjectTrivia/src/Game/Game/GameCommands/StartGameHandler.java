package Game.Game.GameCommands;

import Game.Board.PlayerPieces.Pieces;
import Game.Game.Game;
import Game.Game.Messages.Messages;
import ServerClient.Server.Server;

import java.util.List;

public class StartGameHandler implements GameCommandHandler{

    @Override
    public void execute(Game game, Server.ClientHandler player) {

        if(game.isGameStarted()){
            player.send(Messages.GAME_ALREADY_STARTED);
            return;
        }

        if(player.getMessage().length() > GameCommand.START_GAME.getDescription().length()){
            player.send(Messages.INVALID_USE_OF_COMMAND);
            return;
        }

        if(!game.isGameFull()){
            player.send("Game lobby not full");
            return;
        }

        List<Server.ClientHandler> playerList = game.getPLAYERS();

        game.setGameStarted(true);
        game.start();
        for (Server.ClientHandler clientHandler : playerList) {

            clientHandler.setPiece(Pieces.generatePiece());
            game.getBOARD().setPiece(clientHandler.getPiece());
            clientHandler.send(Messages.GAME_PIECES + clientHandler.getPiece());

        }
        try {
            Thread.sleep(1000);
            game.lobbyBroadcast(game.getBOARD().drawBoard());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        game.printTurnOwner();

    }
}