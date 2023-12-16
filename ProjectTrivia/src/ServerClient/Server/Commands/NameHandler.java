package ServerClient.Server.Commands;

import ServerClient.Server.Messages.Messages;
import ServerClient.Server.Server.Server;

public class NameHandler implements CommandHandler{
    @Override
    public void execute(Server server, Server.ClientHandler playerHandler) {
        String message = playerHandler.getMessage();
        String name = message.substring(6);
        String oldName = playerHandler.getName();
        server.getClientByName(name.toLowerCase()).ifPresentOrElse
                (client -> playerHandler.send(Messages.CLIENT_EXISTS),
                        () -> {
                    playerHandler.setName(name);
                    playerHandler.send(Messages.SELF_NAME_CHANGE + name);
                    server.broadcast(oldName + Messages.NAME_CHANGE + name);
                }
        );
    }
}