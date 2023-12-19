package ServerClient.Server.Commands;

import ServerClient.Server.Messages.Messages;
import ServerClient.Server.Server;
import Game.Game.Game;

public class JoinGameHandler implements CommandHandler {
    @Override
    public void execute(Server server, Server.ClientHandler clientHandler) {

        if(!clientHandler.getMessage().substring(6).matches("[0-9]+")){
            clientHandler.send(Messages.INVALID_USE_OF_COMMAND);
            return;
        }
        int gameId = Integer.parseInt(clientHandler.getMessage().substring(6));

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
        clientHandler.setMyTurn(false);
        game.lobbyBroadcast(clientHandler, clientHandler.getName()+Messages.PLAYER_JOINED_LOBBY);
        clientHandler.send(Messages.JOIN_LOBBY+gameId);
    }
}