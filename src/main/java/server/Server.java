package server;

import data.Client;
import data.Group;

import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    //static ExecutorService executorService = Executors.newFixedThreadPool(4);
    private static List<Client> clientList;
    private static List<Group> groupList;

    private ServerSocket serverSocket;
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Ждем клиентов");

        groupList = new ArrayList<Group>();
        clientList = new ArrayList<Client>();

        groupList.add(new Group(null, "general"));


        int count = 1; //счетчиек клиентов
        while ( !serverSocket.isClosed() ) {

            Socket socket = serverSocket.accept();
            System.out.println("Подключился клиент " + count + " : " + socket.getInetAddress().getHostAddress());

            ClientHandler clientHandler = new ClientHandler(count, socket);
            clientHandler.start();

            count++;
        }

    }

    public synchronized static List<Group> getGroupList() {
        return groupList;
    }

    public synchronized static List<Client> getClientList() {
        return clientList;
    }

    public List<Client> getAllCliet() {
        List<Client> clients = new ArrayList<Client>();
        for (Group group :
                groupList) {
            for (Client cloent :
                    group.getClientList()) {
                clients.add(cloent);
            }
        }
        return clients;
    }


    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        try {
            System.out.println("Адрес сервера: " + Inet4Address.getLocalHost().getHostAddress());
            short port = 7777;
            Server server = new Server(port);
            server.start();

        } catch (UnknownHostException ex) {
            System.err.println("Неудалось узнать IP сервера");
        } catch (IOException ex) {
            System.err.println("IOExeprion on server.Server");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
