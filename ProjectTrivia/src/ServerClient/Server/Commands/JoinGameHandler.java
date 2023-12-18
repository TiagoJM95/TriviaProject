package ServerClient.Server.Commands;

import ServerClient.Server.Messages.Messages;
import ServerClient.Server.Server;
import Game.Game.Game;

public class JoinGameHandler implements CommandHandler {
    @Override
    public void execute(Server server, Server.ClientHandler clientHandler) {
        int gameId = Integer.parseInt(clientHandler.getMessage().substring(10));

        Game game = server.getGameById(gameId);

        if(game == null){
            clientHandler.send(Messages.NO_SUCH_GAME);
            return;
        }
        if(game.isGameFull()){
            clientHandler.send(Messages.FULL_LOBBY);
            return;
        }
        server.addPlayerToGame(clientHandler, gameId);
    }
}