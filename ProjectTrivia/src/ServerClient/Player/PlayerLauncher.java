package ServerClient.Player;

import java.io.IOException;

public class PlayerLauncher {
    public static void main(String[] args) {
        Player player = new Player();
        try {
            player.start("localhost", 8080);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
