package ServerClient.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player {
    private Socket playerSocket;
    private boolean myTurn;

    public Player(){
        this.playerSocket = null;
        this.myTurn = false;
    }

    public void start(String host, int port) throws IOException {
        playerSocket = new Socket(host, port);
        new Thread(new Listener()).start();
        receiveGameMessages();
    }

    private void receiveGameMessages() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));
        String gameMessage = in.readLine();

        if(isCommand(gameMessage)){
            dealWithCommand(gameMessage);
        }
        System.out.println(gameMessage);
    }

    private boolean isCommand(String gameMessage) {
        return gameMessage.startsWith("/");
    }

    private void dealWithCommand(String gameMessage){
        System.out.println("Player.java : dealWithCommand()");
    }

    class Listener implements Runnable{

        @Override
        public void run() {
            try {
                PrintWriter out  = new PrintWriter(playerSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                String message;

                while((message =in.readLine())!=null){
                    if(myTurn){
                        out.println(message);
                    }
                    myTurn = false;
                }
                //quit();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}