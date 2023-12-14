package ServerClient.Server.Server;

import java.io.IOException;

public class ServerLauncher {
    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
