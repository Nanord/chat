package client;

import data.Message;
import data.User;

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
            getIpAddressServer(true);
            out.println("IP адрес server: " + ipAddressServer);

            socket = new Socket(ipAddressServer, serverPort);
            out.println("Подключился к серверу");

            final ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            final ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());


            User user = new User("Alex");
            outputStream.writeObject(new Message(user, "/serverHello", null));

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            while (true) {
                System.out.println("Ваше сообщение: ");
                line = keyboard.readLine();

                Message message;
                if(line.substring(0,1).equalsIgnoreCase("/")) {
                    String[] comm_text = line.split(" ");
                    String comm = comm_text[0];
                    String text = (comm_text.length > 1)? comm_text[1] : null;
                    message = new Message(user, comm, text);
                }
                else {
                    message = new Message(user, "/send", line);
                }
                outputStream.writeObject(message);


                System.out.println("Ответ сервера:" + ((Message)inputStream.readObject()).getData());

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
        Client client = new Client(7777);
        client.run();
    }
}
