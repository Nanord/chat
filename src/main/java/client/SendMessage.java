package client;

import server.InfoSend;
import server.db.model.Message;
import server.db.model.User;

import java.io.*;
import java.util.Scanner;

public class SendMessage implements Runnable{
    private ObjectOutputStream out;

    public SendMessage(ObjectOutputStream out) {
        this.out = out;
    }

    public void run() {
        try {
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line = null;

            System.out.println("Введите ваш ник");
            User user = new User(keyboard.readLine());
            out.writeObject(new Message(user, "/serverHello", null));

            while (true) {
                //System.out.println("Ваше сообщение: ");
                line = keyboard.readLine();

                Message message;
                if (!line.isEmpty() && line.substring(0, 1).equalsIgnoreCase("/")) {
                    String[] comm_text = line.split(" ");
                    String comm = comm_text[0];
                    String text = (comm_text.length > 1) ? comm_text[1] : null;
                    message = new Message(user, comm, text);
                } else {
                    message = new Message(user, "/send", line, "general");
                }
                out.writeObject(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
