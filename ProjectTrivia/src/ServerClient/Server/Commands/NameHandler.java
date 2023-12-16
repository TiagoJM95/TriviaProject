package ServerClient.Server.Commands;

import Game.Game.Game;
import ServerClient.Server.Server.Server;

public class NameHandler implements CommandHandler{
    @Override
    public void execute(Server server, Server.ClientConnectionHandler playerHandler) {
        String message = playerHandler.getMessage();
        String name = message.substring(6);
        String oldName = playerHandler.getName();
        server.getClientByName(name).ifPresentOrElse(
                client -> playerHandler.send("Client already exists"),
                () -> {
                    playerHandler.setName(name);
                    playerHandler.send("You changed your name to " + name);
                    server.broadcast(oldName + " changed name to " + name);
                }
        );
    }
}