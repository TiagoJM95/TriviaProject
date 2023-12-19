package ServerClient.Server.Commands;

import Game.Game.Game;
import ServerClient.Server.Messages.Messages;
import ServerClient.Server.Server;

public class CreateGameHandler implements CommandHandler{
    @Override
    public void execute(Server server, Server.ClientHandler clientHandler) {

        if(clientHandler.getMessage().length() > Command.CREATE_GAME.getDescription().length()){
            clientHandler.send(Messages.INVALID_USE_OF_COMMAND);
            return;
        }

        Server.setGameCounter(Server.getGameCounter()+1);
        server.getGames().add(new Game(Server.getGameCounter(), server));
        server.addPlayerToGame(clientHandler, Server.getGameCounter());

        clientHandler.setMyTurn(true);
        clientHandler.send(Messages.GAME_CREATED);
        server.broadcast(clientHandler, Messages.ALL_GAME_CREATED+Server.getGameCounter());
    }
}