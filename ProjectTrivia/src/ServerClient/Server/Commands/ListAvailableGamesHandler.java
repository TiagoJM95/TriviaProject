package ServerClient.Server.Commands;

import Game.Game.Game;
import ServerClient.Server.Messages.Messages;
import ServerClient.Server.Server;

public class ListAvailableGamesHandler implements CommandHandler {
    @Override
    public void execute(Server server, Server.ClientHandler clientHandler) {
        String gameList = server.getGames().stream()
                .map(game -> "Game "+game.getGameId()+" ➜ "+game.getNumOfPlayers()+"/"+ Game.MAX_PLAYERS+" players")
                .toList()
                .toString();

        if(gameList.length()<3){
            clientHandler.send(Messages.NO_GAME_LOBBY);
            return;
        }
        clientHandler.send(Messages.GAME_LIST);
        clientHandler.send(gameList);
    }
}