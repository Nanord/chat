package client;

import commonData.MessageSend;
import commonData.UserSend;
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



        executorService = Executors.newSingleThreadExecutor();
       // executorService.submit(new ReadMessage(in, user));
       /* Thread thread = new Thread(new ReadMessage(in, user));
        thread.start();*/

      //  SendMessage sendMessage = new SendMessage(out, user);
        //sendMessage.run();


        /*String data = null;
        String nameGroup = "general";
        while (true) {

            //System.out.println("Ваше сообщение: ");
            data = keyboard.readLine();

            Message message = null;
            if (!data.isEmpty() && data.substring(0, 1).equalsIgnoreCase("/")) {
                String[] comm_text = data.split(" ");
                String comm = comm_text[0];
                String text = (comm_text.length > 1) ? comm_text[1] : null;
                if (comm.equalsIgnoreCase("/joinGroup") && comm.equalsIgnoreCase("/createGroup"))
                    nameGroup = text;
                message = new Message(user, comm, text);
            } else {
                message = new Message(user, "/send", data, nameGroup);
            }
            out.writeObject(message);
        }*/
    }

    public static void main(String[] args) throws Exception{
        WorkClient workClient = new WorkClient();
        workClient.run(7804, "10.182.1.123");
    }
}
