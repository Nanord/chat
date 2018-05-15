package client.stressTest;

import server.db.model.Message;
import server.db.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

import static java.lang.System.err;
import static java.lang.System.out;


public class ClientTest extends Thread {
    private static Logger logger = LoggerFactory.getLogger(ClientTest.class);

    private int serverPort;
    private String ipAddressServer;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private Socket socket;

    public ClientTest(int serverPort, int count) {
        this.count = count;
        this.serverPort = serverPort;
    }

    private void getIpAddressServer(boolean status) throws UnknownHostException {
        ipAddressServer = status
                ? Inet4Address.getLocalHost().getHostAddress()
                : System.getProperty("ipServer");
    }

    private void creteSocket() throws IOException {
        socket = new Socket(ipAddressServer, serverPort);
    }

    @Override
    public void run() {

        try {
            getIpAddressServer(true);
            out.println(ipAddressServer + " " + serverPort);

            creteSocket();

           // User user = new User(Integer.toString(count));
            //InfoSend infoSend = new InfoSend(socket);

            //infoSend.sendMessage(new Message(user, "/serverHello", null));
            final ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            final ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            Random random = new Random();
           // User user = new User(String.valueOf(random.nextInt()), String.valueOf(random.nextInt()));
          //  outputStream.writeObject(new Message(user, "/serverHello", null));
            // infoSend.sendMessage(new Message(user, "/serverHello", null));
            out.println(((Message)inputStream.readObject()).getData());

            //BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line = "Hello";

            for(int i = 0; i < 3; i++) {
                //System.out.println("Ваше сообщение: ");
               // line = keyboard.readLine();

                Message message;
                if(line.substring(0,1).equalsIgnoreCase("/")) {
                    String[] comm_text = line.split(" ");
                    String comm = comm_text[0];
                    String text = (comm_text.length > 1)? comm_text[1] : null;
              //      message = new Message(user, comm, text);
                }
                else {
             //       message = new Message(user, "/send", line, "general");
                }
                //infoSend.sendMessage(message);
            //    outputStream.writeObject(message);
                outputStream.flush();

                //System.out.println("Клиенту" + count + ": " + ((Message)inputStream.readObject()).getData());

                //System.out.println("Клиенту " + count + ": " + infoSend.readMessage().getData());
                System.out.println("Ответ сервера:" + ((Message)inputStream.readObject()).getData());
                //logger.info("");
               //System.out.println();
            }
            //infoSend.sendMessage(new Message(user, "/exit", null));
            //outputStream.writeObject(new Message(user, "/exit", null));
            socket.close();
            //infoSend.close();

        } catch (UnknownHostException ex) {
            err.println("Неудалось узнать адресс ");
        } catch (IOException e) {
            out.println(count + " Вышел(IOExeption)");
            e.printStackTrace();
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
        int count = 1;
        for (int i = 0; i < 2; i++) {
            ClientTest client = new ClientTest(7792, count);
            client.start();

            count++;
        }
    }
}
