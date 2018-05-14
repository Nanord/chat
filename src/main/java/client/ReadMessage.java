package client;

import server.InfoSend;
import server.db.model.Message;
import server.db.model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.Callable;

import static java.lang.System.err;
import static java.lang.System.in;
import static java.lang.System.out;

public class ReadMessage implements Runnable {
    private final ObjectInputStream in;

    public ReadMessage(ObjectInputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            while (true) {
                //wait(1);
                Message message = (Message)in.readObject();
                User curUser = message.getUser();

                if(curUser != null) {
                    err.println(curUser.getName() +
                            ": " +
                            message.getData()
                    );
                } else {
                    err.println(message.getData());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
