package server;

import data.User;
import data.Group;
import server.command.CommandHandler;

import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    //static ExecutorService executorService = Executors.newFixedThreadPool(4);
    private static List<User> userList = Collections.synchronizedList(new ArrayList<User>());
    private static List<Group> groupList = Collections.synchronizedList(new ArrayList<Group>());

    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void start() throws IOException {

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Ждем клиентов");

        //Стартовая группа
        groupList.add(new Group(null, "general"));
        //Комманды
        CommandHandler comm = new CommandHandler();

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

    public synchronized static List<User> getUserList() {
        return userList;
    }

    /*public List<User> getAllCliet() {
        List<User> users = new ArrayList<User>();
        for (Group group :
                groupList) {
            for (InfoSend user :
                    group.getUserList()) {
                users.add(user.getUser());
            }
        }
        return users;
    }*/


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
            ex.printStackTrace();
        } catch (Exception ex) {
            System.err.println("ХЗ Exeption");
            ex.printStackTrace();
        }
    }
}
