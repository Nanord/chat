package server;

import commonData.Data;
import server.command.clientCommand.ClientCommandHandler;
import server.command.serverCommand.ServerCommandHandler;

import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.*;

public class Server {
    private ExecutorService executorService;

    private static int port;

    private static ServerSocket serverSocket;

    public Server(int port) {
        //this.executorService = Executors.newCachedThreadPool();
        this.executorService = Executors.newFixedThreadPool(100);
        Server.port = port;
    }

    public void startServer() throws Exception {
        try {
            //Сделать SSLSocket(нужен сертификат:(
            serverSocket = new ServerSocket(port);
            //Выгрузка данных из бд
            DataServer dataServer = new DataServer();
            //Инициализация комманд
            ClientCommandHandler comm = ClientCommandHandler.getInstance();

            int count = 1; //счетчиек клиентов
            System.out.println("Ждем клиентов");
            while (!serverSocket.isClosed()) {

                Socket socket = serverSocket.accept();
                System.out.println("Подключился клиент " + count + " : " + socket.getInetAddress().getHostAddress());

                executorService.submit(new ClientHandler(count, socket));

                count++;
            }
        } catch (IOException ex) {
            System.out.println("IOExeprion on server.Server");
            ex.printStackTrace();
        } finally {
            if(serverSocket != null && !serverSocket.isClosed())
                serverSocket.close();
        }
    }

    private static boolean isStarted() {
        return serverSocket != null && !serverSocket.isClosed();
    }

    public static boolean strop() throws IOException {
        if(isStarted()) {
            serverSocket.close();
            return true;
        } else
            return false;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        Server.port = port;
    }

    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        try {
            Data.reload();
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(ServerCommandHandler.getInstance());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}