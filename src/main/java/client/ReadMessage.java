package client;

import server.db.model.Message;
import server.db.model.User;

import java.io.IOException;
import java.io.ObjectInputStream;

import static java.lang.System.err;

public class ReadMessage implements Runnable {
    private final ObjectInputStream in;
    private User user;

    public ReadMessage(ObjectInputStream in, User user) {
        this.in = in;
        this.user = user;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("123");
                Thread.sleep(100);
                Message message = (Message)in.readObject();
                User curUser = message.getUser();

                if(curUser != null) {
                    if(!curUser.getName().equalsIgnoreCase(user.getName()))
                        err.println(curUser.getName() + ": " + message.getData());
                } else {
                    err.println(message.getData());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
