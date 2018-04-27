import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server extends Thread{
    private int port = 7777;
    private int count;
    private Socket socket;

    public Server(int count, Socket socket) {
        this.socket = socket;
        this.count = count;
        start();
    }

    public void run() {
        try {
            System.out.println("Подключился клиент " + count + ":"  + socket.getInetAddress().getHostAddress());

            InputStream sIn = socket.getInputStream();
            OutputStream sOut = socket.getOutputStream();

            DataInputStream in = new DataInputStream(sIn);
            DataOutputStream out = new DataOutputStream(sOut);

            String line = null;
            while (true) {
                line = in.readUTF();
                System.out.println("Клиент " + count + " пишет : " + line);

                out.writeUTF(line);
                out.flush();
            }
        } catch (IOException ex) {
            System.out.println("Кливент " + count + " отключился");
        } catch (Exception ex) {
            ex.printStackTrace();
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
            int count = 0; //счетчиек клиентов

            ServerSocket serverSocket = new ServerSocket(7777);
            System.out.println("Ждем клиентов");

            while (true) {
                new Server(count, serverSocket.accept());
                count++;
            }
        } catch (UnknownHostException ex) {
            System.out.println("Неудалось узнать IP сервера");
        } catch (IOException ex) {
            System.out.println("IOExeprion on Server");
        }
    }
}
