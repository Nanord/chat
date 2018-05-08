package server;

import data.InfoSend;
import data.User;
import data.Group;
import server.command.CommandHandler;

import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    //Сделать пул потоков с Chached размером потоков, посмотреть как изменить время
    private ExecutorService executorService;


    //
    private static Set<User> userList = Collections.synchronizedSet(new HashSet<User>());
    private static Map<String, Group> groupList = Collections.synchronizedMap(new HashMap<String, Group>());

    private int port;

    public Server(int port) {
        this.executorService = Executors.newCachedThreadPool();
        this.port = port;
    }

    public void startServer() throws IOException {
        //Сделать SSLSocket
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Ждем клиентов");

        //Стартовая группа
        groupList.put("general" ,new Group("general"));
        //Комманды
        CommandHandler comm = new CommandHandler();

        int count = 1; //счетчиек клиентов
        while ( !serverSocket.isClosed() ) {

            Socket socket = serverSocket.accept();
            System.out.println("Подключился клиент " + count + " : " + socket.getInetAddress().getHostAddress());

            executorService.submit(new ClientHandler(count, socket));

            count++;
        }
    }

    public static Group getMainGoup() {
        return groupList.get("general");
    }

    public static boolean addUser(User user, InfoSend infoSend) {
        if(userList.contains(user)) {
            return false;
        } else {
            userList.add(user);
            groupList.get("general").addUser(user, infoSend);
            return true;
        }
    }

    public static Group joinGroup(String nameGroup, User user, InfoSend infoSend) {
        Group group = groupList.get(nameGroup);
        if(group != null) {
            group.addUser(user, infoSend);
            return group;
        } else {
            return null;
        }
    }

    public static Group getGroup(String namGroup) {
        return groupList.get(namGroup);
    }

    public static void addGroup(Group group) {
        groupList.put(group.getNameGroup(), group);
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        try {
            System.out.println("Адрес сервера: " + Inet4Address.getLocalHost().getHostAddress());
            short port = 7775;
            Server server = new Server(port);
            server.startServer();

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