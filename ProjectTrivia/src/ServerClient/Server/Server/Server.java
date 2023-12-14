package ServerClient.Server.Server;

import ServerClient.Server.Commands.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private ServerSocket serverSocket;
    private ExecutorService service;
    private final List<PlayerHandler> players;

    public Server(){
        players = new ArrayList<>();    //new CopyOnWriteArrayList<>()
    }

    public void start() throws IOException {
        serverSocket = new ServerSocket(8080);
        service = Executors.newCachedThreadPool();
        acceptConnection();
    }

    private void acceptConnection() throws IOException {
        Socket playerSocket = serverSocket.accept();
        PlayerHandler playerHandler = new PlayerHandler();
        service.submit(playerHandler);
    }

    private void addPlayer(PlayerHandler playerHandler){
        players.add(playerHandler);
        //Mensagem de boas vindas;
        //Regras do jogo;
        //Pedir nome ao jogador;
    }

    public void remove(PlayerHandler playerHandler){
        players.remove(playerHandler);
    }



   public class PlayerHandler implements Runnable{
        private String name;
        private final Socket playerSocket;
        private final BufferedReader in;
        private final PrintWriter out;
        private String input;


        public PlayerHandler(Socket playerSocket) throws IOException {
            this.playerSocket = playerSocket;
            in = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));
            out = new PrintWriter(playerSocket.getOutputStream(), true);
        }

        @Override
        public void run() {
            addPlayer(this);
            while(!playerSocket.isClosed()){
                try {
                    input = in.readLine();
                    if(isCommand(input)){
                        command(input);
                        continue;
                    }
                    //you need to input commands;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        private boolean isCommand(String input){
            return input.startsWith("/");
        }

        private void command(String input){
            String commandDescription = input.split(" ")[0];
            Command command = Command.getCommand(commandDescription);

            command.getHandler().execute(Server.this, this);
        }

        public void send(String input){
            out.println(input);
        }

        public void close(){
            try {
                playerSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }

        public String getInput() {
            return input;
        }
    }
}
