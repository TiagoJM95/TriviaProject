package ServerClient.Server.Commands;

import ServerClient.Server.Server.Server;

@FunctionalInterface
public interface CommandHandler {
    void execute(Server server, Server.ClientConnectionHandler clientConnectionHandler);
}
