package server;

import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server implements Runnable{
    private int count;
    private Socket socket;

    public Server(int count, Socket socket) {
        this.socket = socket;
        this.count = count;
        final Thread serverThread = new Thread(this, "server.Server");
        serverThread.start();
    }

    @Override
    public void run() {
        try {
            System.out.println("Подключился клиент " + count + ":"  + socket.getInetAddress().getHostAddress());

            InputStream sIn = socket.getInputStream();
            OutputStream sOut = socket.getOutputStream();

            DataInputStream in = new DataInputStream(sIn);
            DataOutputStream out = new DataOutputStream(sOut);

            String message = null;
            while (!socket.isClosed()) {
                message = in.readUTF();
                System.out.println("Клиент " + count + " пишет : " + message);

                if(message.equalsIgnoreCase("-q")) {
                    System.out.println("Клиент " + count + " отключился");
                    socket.close();
                } else {
                    out.writeUTF(message);
                    out.flush();
                }
            }

            in.close();
            out.close();
            socket.close();

        } catch (IOException ex) {
            System.err.println("Кливент " + count + "  неожиданно отключился");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        try {
            System.out.println("Адрес сервера: " + Inet4Address.getLocalHost().getHostAddress());
            int count = 0; //счетчиек клиентов

            ServerSocket serverSocket = new ServerSocket(7777);
            System.out.println("Ждем клиентов");

            while (true) {
                new Server(count, serverSocket.accept());
                count++;
            }
        } catch (UnknownHostException ex) {
            System.err.println("Неудалось узнать IP сервера");
        } catch (IOException ex) {
            System.err.println("IOExeprion on server.Server");
        }
    }
}
