package client;

import commonData.MessageSend;
import commonData.UserSend;
import server.db.model.Message;
import server.db.model.User;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.*;

public class WorkClient{

    private Socket socket;

    private void run(int port, String ip) throws IOException {
        try {
            socket = new Socket(ip, port);
            System.out.println("Подключение к серверу успешно");

            final ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            final ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            //Регистрация
            System.out.println("Введите ваш логин:");
            String login = keyboard.readLine();
            System.out.println("Введите ваш пароль:");
            String password = keyboard.readLine();
            UserSend user = new UserSend(login, password, 0);
            out.writeObject(new MessageSend(user, "/serverHello", null, null));

            //Получение id и название стартовой группы
            MessageSend conf = (MessageSend) in.readObject();
            user = conf.getUser();
            String groupName = conf.getNameGroup();
            System.err.println(conf.getData());

            //Обмен данными между потоками
            Exchanger<String> ex = new Exchanger<String>();

            //Создание потока на чтение сообщений
            Thread thread = new Thread(new ReadMessage(in, user, ex));
            thread.start();

            //Передача сообщений на сервер
            SendMessage sendMessage = new SendMessage(out, user, groupName, ex);
            sendMessage.run();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }

    }

    public static void main(String[] args) throws Exception{
        WorkClient workClient = new WorkClient();
        workClient.run(7837, "10.182.1.123");
    }
}
