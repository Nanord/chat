package client.stressTest;

import commonData.MessageSend;
import commonData.UserSend;

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
                : "10.182.1.123";
    }


    public void run() throws IOException, ClassNotFoundException{
        try {
            getIpAddressServer(false);
            out.println("IP адрес server: " + ipAddressServer);

            socket = new Socket(ipAddressServer, serverPort);
            out.println("Подключился к серверу");

            final ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            final ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            //InfoSend infoSend = new InfoSend(socket);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Введите ваш логин:");
            String login = keyboard.readLine();
            System.out.println("Введите ваш пароль:");
            String password = keyboard.readLine();
            UserSend user = new UserSend(login, password, 0);

            outputStream.writeObject(new MessageSend(user, "/serverHello", null, null));
            // infoSend.sendMessage(new Message(user, "/serverHello", null));
            MessageSend messageSend = ((MessageSend)inputStream.readObject());
            out.println(messageSend.getData());
            user = messageSend.getUser();
            String groupName = messageSend.getNameGroup();

            //out.println(infoSend.readMessage().getData());

            String line = null;
            while (true) {
                System.out.println("Ваше сообщение: ");
                line = keyboard.readLine();

                MessageSend message;
                if(line.substring(0,1).equalsIgnoreCase("/")) {
                    String[] comm_text = line.split(" ");
                    String comm = comm_text[0];
                    String text = (comm_text.length > 1)? comm_text[1] : null;
                    message = new MessageSend(user, comm, text, groupName);
                }
                else {
                    message = new MessageSend(user, "/send", line, groupName);
                }
                if(message.getData() != null) {
                    outputStream.writeObject(message);
                    outputStream.flush();
                }
                else
                    continue;

                System.out.println(((MessageSend)inputStream.readObject()).getData());

                System.out.println();
            }
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
        Client client = new Client(7832);
        client.run();
    }
}
