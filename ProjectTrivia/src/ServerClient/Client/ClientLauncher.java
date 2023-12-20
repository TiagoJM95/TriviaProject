package ServerClient.Client;


import java.io.IOException;

public class ClientLauncher {
    public static void main(String[] args) {
        Client client = new Client();

        try {
            client.start("localhost", 8500);
        } catch (IOException e) {
            System.out.println("Connection closed...");
        }
    }
}