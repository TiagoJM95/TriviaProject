package ServerClient.Player;


import java.io.IOException;

public class ClientLauncher {
    public static void main(String[] args) {
        Client client = new Client();

        try {
            client.start("localhost", 8080);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
