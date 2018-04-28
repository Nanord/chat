package client;

import java.io.*;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;


import static java.lang.System.*;

public class Client {
    private int serverPort;
    private String ipAddressServer;

    private Socket socket;

    public Client(int serverPort) {
        this.serverPort = serverPort;
    }

    private void getIpAddressServer(boolean status) throws  UnknownHostException{
        ipAddressServer = status
                ? Inet4Address.getLocalHost().getHostAddress()
                : System.getProperty("ipServer");
    }

    private void creteSocket() throws IOException {
        socket = new Socket(ipAddressServer, serverPort);
    }

    public void run() {
        try {
            getIpAddressServer(false);
            out.println("IP адрес server: " + ipAddressServer);

            socket = new Socket(ipAddressServer, serverPort);
            out.println("Подключился к серверу");

            InputStream sIn = socket.getInputStream();
            OutputStream sOut = socket.getOutputStream();

            DataInputStream in = new DataInputStream(sIn);
            DataOutputStream out = new DataOutputStream(sOut);

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            while (true) {
                System.out.println("Ваше сообщение: ");
                line = keyboard.readLine();
                out.writeUTF(line);
                out.flush();

                line = in.readUTF();
                System.out.println("Ответ сервера: " + line);

                System.out.println();
            }

        } catch (UnknownHostException ex) {
            err.println("Неудалось узнать адресс ");
        } catch (IOException e) {
            err.println("IOExeption on Clint");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        Client client = new Client(Integer.parseInt(System.getProperty("port")));
        client.run();
    }
}
