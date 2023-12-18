package ServerClient.Server.Commands;

import ServerClient.Server.Messages.Messages;
import ServerClient.Server.Server;

public class NameHandler implements CommandHandler{
    @Override
    public void execute(Server server, Server.ClientHandler clientHandler) {
        String message = clientHandler.getMessage();
        String name = message.substring(6);
        if(name.matches(("[A-Z]'?[a-zA-Z]+(-[a-zA-Z]+)?$"))){
            String oldName = clientHandler.getName();
            server.getClientByName(name).ifPresentOrElse
                    (client -> clientHandler.send(Messages.CLIENT_EXISTS),
                            () -> {
                                clientHandler.setName(name);
                                clientHandler.send(Messages.SELF_NAME_CHANGE + name);
                                server.broadcast(clientHandler,oldName + Messages.NAME_CHANGE + name);
                            }
                    );
            return;
        }
        clientHandler.send(Messages.INVALID_NAME);
    }
}