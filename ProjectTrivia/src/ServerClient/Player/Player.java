package ServerClient.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player {
    private Socket playerSocket;
    //private boolean myTurn;

    public void start(String host, int port) throws IOException {
        playerSocket = new Socket(host, port);
        BufferedReader serverIn = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));
        PrintWriter serverOut = new PrintWriter(playerSocket.getOutputStream(), true);
        BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));

        Thread listenerThread = new Thread(new Listener(serverIn));
        listenerThread.start();

        String messageToServer = "";
        while(!messageToServer.equals("/exit")) {
            messageToServer = consoleIn.readLine();
            serverOut.println(messageToServer);
        }

        try {
            listenerThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        serverIn.close();
        serverOut.close();
        consoleIn.close();
        playerSocket.close();
    }

    static class Listener implements Runnable{
        private final BufferedReader in;

        Listener(BufferedReader in){
            this.in = in;
        }

        @Override
        public void run() {
            String messageFromServer = null;
            try {
                while (!(messageFromServer = in.readLine()).equals("exit")) {
                    System.out.println(messageFromServer);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}