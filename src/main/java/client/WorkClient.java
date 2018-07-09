package client;

import commonData.MessageSend;
import commonData.UserSend;
import server.db.model.Message;
import server.db.model.User;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

public class WorkClient{

    private Socket socket;

    public void run(int port, String ip) throws IOException {
        try {
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            //Регистрация
            System.out.println("Введите ваш логин:");
            String login = keyboard.readLine();
            System.out.println("Введите ваш пароль:");
            String password = keyboard.readLine();
            UserSend user = new UserSend(login, password, 0);

            while (true) {
                try {
                    socket = new Socket(ip, port);
                    System.out.println("Подключение к серверу успешно");

                    final ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    final ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    out.writeObject(new MessageSend(user, "/serverHello", null, null));

                    //Получение id и название стартовой группы
                    MessageSend conf = (MessageSend) in.readObject();
                    String groupName = null;
                    if(conf.getUser() != null && conf.getNameGroup() != null) {
                        user = conf.getUser();
                        groupName = conf.getNameGroup();
                        System.err.println(conf.getData());
                    } else {
                        System.out.println("Логин занят");
                        close();
                        break;
                    }


                    //Обмен данными между потоками
                    Exchanger<String> ex = new Exchanger<String>();

                    //Создание потока на чтение сообщений
                    Thread thread = new Thread(new ReadMessage(in, user, ex));
                    thread.start();

                    //Передача сообщений на сервер
                    SendMessage sendMessage = new SendMessage(out, user, groupName, ex);
                    sendMessage.run();
                } catch (SocketException ignored) {
                    Thread.sleep(1000);
                }
            }
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            close();
        }

    }

    private void close() throws IOException {
        if(socket != null) {
            socket.close();
        }
    }
}
