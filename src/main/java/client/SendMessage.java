package client;

import commonData.MessageSend;
import commonData.UserSend;
import server.db.model.Message;
import server.db.model.User;
import sun.misc.resources.Messages;

import java.io.*;
import java.net.SocketException;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SendMessage {
    private Exchanger<String> exchanger;


    private ObjectOutputStream out;
    private UserSend user;
    private String groupName;

    SendMessage(ObjectOutputStream out, UserSend user, String groupName,  Exchanger<String> exchanger) {
        this.out = out;
        this.user = user;
        this.exchanger = exchanger;
        this.groupName = groupName;
    }

    public void run() {
        try {
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String data = null;
            while (true) {
                data = keyboard.readLine();
                MessageSend message = null;
                //Считываем комманду
                if (!data.isEmpty() && data.substring(0, 1).equalsIgnoreCase("/")) {
                    String[] comm_text = data.split(" ");
                    String comm = comm_text[0];
                    StringBuilder text = new StringBuilder();
                    for (int i = 0; i < comm_text.length; i++) {
                        text.append(comm_text[i]);
                    }
                    message = new MessageSend(user, comm, text.toString(), groupName);
                }
                //Если команда не была введена, то отправляем сообщение в тек. группу
                else {
                    message = new MessageSend(user, "/send", data, groupName);
                }
                out.writeObject(message);

                //Проверяем на наличие изменения группы
                try {
                    groupName = exchanger.exchange("", 500, TimeUnit.MILLISECONDS);
                } catch (TimeoutException ignored) {
                }
            }
        } catch (SocketException ignored) {
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
