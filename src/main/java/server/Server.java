package server;

import server.command.clientCommand.ClientCommandHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.*;

public class Server{

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
            //Инициализация комманд
            ClientCommandHandler comm = ClientCommandHandler.getInstance();



            int count = 1; //счетчиек клиентов
            System.out.println("Ждем клиентов");
            while (serverSocket != null && !serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("Подключился клиент " + count + " : " + socket.getRemoteSocketAddress().toString());

                executorService.submit(new ClientHandler(count, socket));

                count++;
            }
        } catch (SocketException ignored) {
        } catch (IOException ex) {
            System.err.println("IOExeprion on server.Server");
            ex.printStackTrace();
        } finally {
            if(serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                serverSocket.getChannel().close();
            }
        }
    }

    public static boolean isStarted() {
        return serverSocket != null && !serverSocket.isClosed();
    }

    public static synchronized  boolean stop() throws IOException {
        if(isStarted()) {
            serverSocket.close();
            serverSocket.getChannel().close();

            serverSocket = null;
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
}