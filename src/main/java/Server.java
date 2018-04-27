import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Ждем клиента");

            Socket socket = serverSocket.accept();

            System.out.println("Подключился клиент");

            InputStream sIn = socket.getInputStream();
            OutputStream sOut = socket.getOutputStream();

            DataInputStream in = new DataInputStream(sIn);
            DataOutputStream out = new DataOutputStream(sOut);

            String line = null;
            while (true) {
                line = in.readUTF();
                System.out.println("Клиент пишет : " + line);

                out.writeUTF(line);
                out.flush();
            }
        } catch (IOException ex) {
            System.out.println("IOExeprion on Server");
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
       Server server = new Server(7777);
       server.run();
    }
}
