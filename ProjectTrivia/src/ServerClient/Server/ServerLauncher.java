package ServerClient.Server;

import ServerClient.Server.Server;

public class ServerLauncher {
    public static void main(String[] args) {
        Server server = new Server();

        server.start(8500);
    }
}