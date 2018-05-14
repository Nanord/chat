package client;

import server.InfoSend;
import server.db.model.Message;
import server.db.model.User;

import java.io.*;
import java.util.Scanner;

public class SendMessage {
    private ObjectOutputStream out;
    private User user;

    public SendMessage(ObjectOutputStream out, User user) {
        this.out = out;
        this.user = user;
    }

    public void run() {
        try {
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String data = null;

            String nameGroup = "general";
            while (true) {

                //System.out.println("Ваше сообщение: ");
                data = keyboard.readLine();

                Message message = null;
                if (!data.isEmpty() && data.substring(0, 1).equalsIgnoreCase("/")) {
                    String[] comm_text = data.split(" ");
                    String comm = comm_text[0];
                    String text = (comm_text.length > 1) ? comm_text[1] : null;
                    message = new Message(user, comm, text, nameGroup);
                    if (comm.equalsIgnoreCase("/joinGroup") && comm.equalsIgnoreCase("/createGroup"))
                        nameGroup = text;
                } else {
                    message = new Message(user, "/send", data, nameGroup);
                }
                out.writeObject(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
