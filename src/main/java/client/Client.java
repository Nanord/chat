package client;

import data.InfoSend;
import data.Message;
import data.User;

import java.io.*;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;


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


    public void run() throws IOException, ClassNotFoundException{
        try {
            getIpAddressServer(true);
            out.println("IP адрес server: " + ipAddressServer);

            socket = new Socket(ipAddressServer, serverPort);
            out.println("Подключился к серверу");

            final ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            final ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            //InfoSend infoSend = new InfoSend(socket);

            Random random = new Random();
            User user = new User(String.valueOf(random.nextInt()));
            outputStream.writeObject(new Message(user, "/serverHello", null));
           // infoSend.sendMessage(new Message(user, "/serverHello", null));
            out.println(((Message)inputStream.readObject()).getData());
            //out.println(infoSend.readMessage().getData());

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
                outputStream.flush();
                //infoSend.sendMessage(message);


                System.out.println("Ответ сервера:" + ((Message)inputStream.readObject()).getData());
                //out.println(infoSend.readMessage().getData());

                System.out.println();
            }
            //infoSend.close();
        } catch (UnknownHostException ex) {
            err.println("Неудалось узнать адресс ");
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
    public static void main(String[] args) throws Exception {
        Client client = new Client(7791);
        client.run();
    }
}
