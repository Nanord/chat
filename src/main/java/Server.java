import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

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
          System.out.println("IOExeption с получением или отправлением сообщения");
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


    public static void main(String[] args) {
        try {
            int count = 0; //счетчиек клиентов

            ServerSocket serverSocket = new ServerSocket(7777);
            System.out.println("Ждем клиентов");

            while (true) {
                new Server(count, serverSocket.accept());
                count++;
            }
        } catch (IOException ex) {
            System.out.println("IOExeprion on Server");
        }
    }
}
