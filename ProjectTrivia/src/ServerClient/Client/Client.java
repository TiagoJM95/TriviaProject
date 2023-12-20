package ServerClient.Client;

import ServerClient.Server.Messages.Messages;

import java.io.*;
import java.net.Socket;

public class Client {

    public void start(String host, int port) throws IOException {

        Socket socket = new Socket(host, port);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        new Thread(new ClientHandler(out, socket)).start();

        String line;
        while (( line = in.readLine()) != null) {
            System.out.println(line);
        }

        socket.close();
    }

    private static class ClientHandler implements Runnable {
        private final BufferedWriter out;
        private final Socket socket;
        private final BufferedReader in;

        public ClientHandler(BufferedWriter out, Socket socket) {
            this.out = out;
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(System.in));
        }

        @Override
        public void run() {

            while (!socket.isClosed()) {
                try {
                    String line = in.readLine();

                    out.write(line);
                    out.newLine();
                    out.flush();

                    if (line.equals("/disconnect")) {
                        socket.close();
                        System.exit(0);
                    }

                } catch (IOException e) {
                    System.out.println(Messages.SERVER_ERROR_2);
                    try {
                        socket.close();
                    } catch (IOException ex) {
                        System.out.println(Messages.SERVER_ERROR_3);
                    }
                }
            }
        }
    }
}