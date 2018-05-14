package client;

import server.InfoSend;
import server.db.model.Message;
import server.db.model.User;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.*;

public class WorkClient{
    private ExecutorService executorService;

    public void run(int port, String ip) throws IOException {
        Socket socket = new Socket(ip, port);
        System.out.println("Подключение к серверу успешно");

        //InfoSend infoSend = new InfoSend(socket);
        final ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        final ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

        executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new ReadMessage(inputStream));

        SendMessage sendMessage = new SendMessage(outputStream);
        sendMessage.run();


    }

    public static void main(String[] args) throws Exception{
        WorkClient workClient = new WorkClient();
        workClient.run(7797, "10.182.1.123");
    }
}
