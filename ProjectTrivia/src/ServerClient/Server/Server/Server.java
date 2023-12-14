package ServerClient.Server.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import Game.Game.Game;

public class Server {
    private ServerSocket serverSocket;
    private ExecutorService service;
    private Game game;

    public void start() throws IOException {
       serverSocket = new ServerSocket(8080);
       service = Executors.newCachedThreadPool();
       System.out.println("Server started at port 8080");

       while(!serverSocket.isClosed()){

           if(!gameCreated()){
               System.out.println("Create a game!");
              createGame();
           }

           if(!gameHasStarted()){
               game.acceptPlayer(serverSocket.accept());
           }

       }
    }

    private boolean gameHasStarted() {
        return game.isGameStarted();
    }

    private void createGame() {
        this.game = new Game(this);
        service.execute(game);
    }

    private boolean gameCreated() {
        return game != null;
    }
}