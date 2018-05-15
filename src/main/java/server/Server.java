package server;

import commonData.InfoSend;
import server.db.model.User;
import server.db.model.Group;
import server.command.CommandHandler;
import server.db.service.GroupService;
import server.db.service.UserService;

import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.*;

public class Server {
    private ExecutorService executorService;


    //Скорее всего эти списки нужно оборачивать из за одновременного чтения через итератор и записи в коллекцию
    //private static Set<User> userList = Collections.synchronizedSet(new HashSet<User>());
    //private static Map<String, GroupSend> groupList = Collections.synchronizedMap(new HashMap<String, GroupSend>());

    //private static ConcurrentSkipListSet<User> userList = new ConcurrentSkipListSet<User>();
    //private static ConcurrentHashMap<String, Group> groupMap = new ConcurrentHashMap<String, Group>();

    private int port;

    public Server(int port) {
        //this.executorService = Executors.newCachedThreadPool();
        this.executorService = Executors.newFixedThreadPool(50);
        this.port = port;
    }

    private void startServer() throws IOException {
        //Сделать SSLSocket(нужен сертификат:(
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Ждем клиентов");
        //Выгрузка данных из бд
        DataServer dataServer = new DataServer();
        //Инициализация комманд
        CommandHandler comm = CommandHandler.getInstance();

        int count = 1; //счетчиек клиентов
        while ( !serverSocket.isClosed() ) {

            Socket socket = serverSocket.accept();
            System.out.println("Подключился клиент " + count + " : " + socket.getInetAddress().getHostAddress());

            executorService.submit(new ClientHandler(count, socket));

            count++;
        }
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
            short port = 7824;
            Server server = new Server(port);
            server.startServer();


        } catch (UnknownHostException ex) {
            System.out.println("Неудалось узнать IP сервера");
        } catch (IOException ex) {
            System.out.println("IOExeprion on server.Server");
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println("ХЗ Exeption");
            ex.printStackTrace();
        }
    }
}