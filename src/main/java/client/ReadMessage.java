package client;

import commonData.MessageSend;
import commonData.UserSend;
import server.db.model.Message;
import server.db.model.User;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketException;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static java.lang.System.err;
import static java.lang.System.out;

public class ReadMessage implements Runnable {
    private Exchanger<String> exchanger;

    private final ObjectInputStream in;
    private UserSend user;

    ReadMessage(ObjectInputStream in, UserSend user, Exchanger<String> exchanger) {
        this.in = in;
        this.user = user;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            while (true) {
                MessageSend message = (MessageSend) in.readObject();
                UserSend curUser = message.getUser();

                if(message.getData().split(":")[0].equals("ResponseServer") && curUser == null) {
                    err.println(message.getData());
                    //Сервер всегда нам отправляет группу, которой мы принадлежим
                    String threadMessage = message.getNameGroup();
                    try {
                        String a = exchanger.exchange(threadMessage, 500, TimeUnit.MILLISECONDS);
                    } catch (TimeoutException e) {
                    }
                } else {
                    if(!curUser.getName().equalsIgnoreCase(user.getName()))
                        out.println(curUser.getName() + ": " + message.getData());
                }
            }
        } catch (SocketException | EOFException ignored) {
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public UserSend getUser() {
        return user;
    }

    public void setUser(UserSend user) {
        this.user = user;
    }
}
