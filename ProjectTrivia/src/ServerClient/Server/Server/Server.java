package ServerClient.Server.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import Game.Game.Game;

public class Server {
    private Game game;

    public void start() throws IOException {
       ServerSocket serverSocket = new ServerSocket(8080);
       ExecutorService service = Executors.newCachedThreadPool();

       System.out.println("Server started at port 8080");

       while(serverSocket.isBound()){

           if(game == null){
               this.game = new Game(this);
               service.execute(game);
               System.out.println("New game created");
           }

           if(game.isAcceptingPlayers()){
               game.acceptPlayer(serverSocket.accept());
           }

       }

       serverSocket.close();
       service.close();
    }
}