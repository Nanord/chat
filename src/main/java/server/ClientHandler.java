package server;

import data.Group;
import data.Message;
import data.User;
import server.command.CommandHandler;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler extends Thread{
    private InfoSend infoSend;
    private int count;

    public ClientHandler(int count, Socket socket) throws IOException{
        this.infoSend = new InfoSend(socket);
        this.count = count;
    }

    @Override
    public void run() {
        try {

            //Сообщение приветсвия
            if (infoSend.readMessage().getCommandText().equalsIgnoreCase("/serverHello"));
            {
                while (!infoSend.getSocket().isClosed()) {
                    Message message = infoSend.readMessage();
                    CommandHandler.makeCommand(message, infoSend);
                }
            }

            infoSend.close();//возможна проблема с тем что сокет может быть закрыт

        } catch (SocketException ex) {
            System.out.println(count + "Вышел(SocketExeprion");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.err.println("Кливент " + count + "  неожиданно отключился");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Присланный объект не соответствует протоколу");
            ex.printStackTrace();
        }
    }

    public Group getGroup(String name) {
        for (Group group:
                Server.getGroupList()) {
            if(group.getNameGroup().equalsIgnoreCase(name)) {
                return group;
            }
        }
        return null;
    }

}
