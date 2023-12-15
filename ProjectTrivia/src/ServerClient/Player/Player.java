package ServerClient.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player {
    private Socket playerSocket;
    BufferedReader consoleIn;
    BufferedReader serverIn;
    PrintWriter serverOut;


    public void start(String host, int port) throws IOException {
        initialize(host, port);
        Thread thread = new Thread(new Listener(serverIn));
        thread.start();
        sendMessages();
    }

    private void sendMessages() throws IOException {
        String messageToServer;

        while(!playerSocket.isClosed()){

            messageToServer = consoleIn.readLine();
            serverOut.println(messageToServer);

        }
    }

    private void initialize(String host, int port) throws IOException {

        playerSocket = new Socket(host, port);
        consoleIn = new BufferedReader(new InputStreamReader(System.in));
        serverIn = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));
        serverOut = new PrintWriter(playerSocket.getOutputStream(), true);

    }

    static class Listener implements Runnable{
        private final BufferedReader in;

        public Listener(BufferedReader serverIn){
            this.in = serverIn;
        }

        @Override
        public void run() {

            String messageFromServer = "A";

            try{
                while(!(messageFromServer = in.readLine()).equals("disconnect")){
                    System.out.println(messageFromServer);
                }
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }
}