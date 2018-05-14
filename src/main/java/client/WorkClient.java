package client;

import server.InfoSend;
import server.db.model.Message;
import server.db.model.User;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class WorkClient{

    public void run(int port, String ip) throws IOException, ClassNotFoundException {
        Socket socket = new Socket(ip, port);
        System.out.println("Подключение к серверу успешно");
        InfoSend infoSend = new InfoSend(socket);

        infoSend.sendMessage(new Message(new User("123"), "/serverHello", null));
        System.out.println("2");
        System.out.println(infoSend.readMessage().getData());

        Reader keyboard = new InputStreamReader(System.in);
        String line = null;
        while (!infoSend.isClosed()) {

        }
    }

    public static void main(String[] args) throws Exception{
        WorkClient workClient = new WorkClient();
        workClient.run(7793, "localhost");
    }
}
