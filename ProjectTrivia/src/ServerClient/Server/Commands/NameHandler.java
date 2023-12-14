package ServerClient.Server.Commands;

import ServerClient.Server.Server.Server;

public class NameHandler implements CommandHandler{
    @Override
    public void execute(Server server, Server.PlayerHandler playerHandler) {
        System.out.println("ola");
    }
}
