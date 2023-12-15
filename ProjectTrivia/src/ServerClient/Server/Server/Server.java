package ServerClient.Server.Server;

import ServerClient.Server.Commands.Command;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import ServerClient.Server.Messages.Messages;

public class Server {

    private ServerSocket serverSocket;
    private ExecutorService service;
    private final List<ClientConnectionHandler> clients;
    private final int MAX_NUMBER_OF_CONNECTIONS = 2;

    public Server() {
        clients = new CopyOnWriteArrayList<>();
    }

    public void start(int port){

        try {
            serverSocket = new ServerSocket(port);
            service = Executors.newFixedThreadPool(MAX_NUMBER_OF_CONNECTIONS);
            System.out.printf(Messages.SERVER_STARTED, port);

            int numOfConnections = 0;

            while(numOfConnections<MAX_NUMBER_OF_CONNECTIONS){

                acceptConnection(numOfConnections);
                numOfConnections++;

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void acceptConnection(int num) throws IOException {
        Socket clientSocket = serverSocket.accept();
        ClientConnectionHandler clientConnectionHandler = new ClientConnectionHandler(clientSocket, "Client" + num);
        service.submit(clientConnectionHandler);
    }

    private void addClient(ClientConnectionHandler clientConnectionHandler){
        clients.add(clientConnectionHandler);
        clientConnectionHandler.send("Welcome " + clientConnectionHandler.getName());
        clientConnectionHandler.send(Messages.COMMAND_LIST);
        broadcast(clientConnectionHandler.getName() + " has connected to this server");
    }

    public void broadcast(String message){
        clients.forEach(handler -> handler.send(message));
    }

    private void listClients(){
       clients.stream()
               .filter(handler -> !handler.equals(this))
               .forEach(handler -> handler.send(handler.getName()));
    }

    private void removeClient(ClientConnectionHandler clientConnectionHandler){
        clients.remove(clientConnectionHandler);
    }

    public Optional<ClientConnectionHandler> getClientByName(String name){
        return clients.stream()
                .filter(client -> client.getName().equals(name))
                .findFirst();
    }


    public class ClientConnectionHandler implements Runnable {

        private String name;
        private final Socket clientSocket;
        private final BufferedWriter out;
        private String message;

        public ClientConnectionHandler(Socket clientSocket, String name) throws IOException {
            this.clientSocket = clientSocket;
            this.name = name;
            this.out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        }

        @Override
        public void run() {
            addClient(this);
            Scanner in = null;
            try {
                in = new Scanner(clientSocket.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            while(in.hasNext()){

                message = in.nextLine();
                if(isCommand(message)){
                    dealWithCommand(message);
                    continue;
                }
                if (message.equals("")){
                    throw new RuntimeException();
                }
            }
        }

        private boolean isCommand(String message){
            return message.startsWith("/");
        }

        private void dealWithCommand(String message){
            String description = message.split(" ")[0];
            Command command = Command.getCommand(description);

            command.getHandler().execute(Server.this, this);
        }

        public void send(String message){
            try {
                out.write(message);
                out.newLine();
                out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void close(){
            try {
                clientSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMessage() {
            return message;
        }
    }
}