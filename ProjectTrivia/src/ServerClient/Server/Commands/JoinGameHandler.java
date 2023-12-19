package ServerClient.Server.Commands;

import ServerClient.Server.Messages.Messages;
import ServerClient.Server.Server;
import Game.Game.Game;

public class JoinGameHandler implements CommandHandler {
    @Override
    public void execute(Server server, Server.ClientHandler clientHandler) {

        String message = clientHandler.getMessage().substring(6);

        if(!message.equals("[0-9]+")){
            clientHandler.send("Invalid use of the command!");
            return;
        }
        int gameId = Integer.parseInt("message");

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
        clientHandler.send(Messages.JOIN_LOBBY+gameId);
        game.lobbyBroadcast(clientHandler, clientHandler.getName()+Messages.PLAYER_JOINED_LOBBY);
    }
}