package server;

import commonData.InfoSend;
import commonData.MessageSend;
import commonData.UserSend;
import server.command.clientCommand.ClientCommandHandler;
import server.db.model.User;
import server.subscription.EventManager;
import server.subscription.EventType;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler implements Runnable{
    private static EventManager eventManager = EventManager.getInstance();

    private InfoSend infoSend;
    private int count;

    public ClientHandler(int count, Socket socket) throws IOException{
        this.infoSend = new InfoSend(socket);
        this.count = count;
    }

    @Override
    public void run() {
        try {
            MessageSend msg = infoSend.readMessage();
            if(msg.getUser() != null)
                infoSend.setUserSend(msg.getUser());
            while (!infoSend.isClosed()) {
                ClientCommandHandler.makeCommand(msg, infoSend);
                msg = infoSend.readMessage();
            }
            if (!infoSend.isClosed())
                infoSend.close();
        } catch (SocketException ex) {
            System.out.println(count + " Вышел(SocketExeprion)");

        } catch (EOFException ex) {
            System.err.println("Ошибка чтения сообщения из потока");

        } catch (IOException ex) {
            System.err.println("Кливент " + count + "  неожиданно отключился");

        } catch (ClassNotFoundException ex) {
            System.err.println("Присланный объект не соответствует протоколу");

        } finally {
            if(infoSend != null) {
                User user = DataServer.ifUserName(infoSend.getUserSend());
                if (user != null) {
                    user.getGroupList().forEach( x -> x.removeOnlineUser(infoSend));
                }
                eventManager.notify(EventType.USERS_EXIT, infoSend.getUserSend().getName());
                System.out.println(infoSend.getUserSend().getName() + " Вышел");
                try {
                    infoSend.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
