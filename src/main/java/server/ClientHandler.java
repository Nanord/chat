package server;

import commonData.InfoSend;
import server.command.CommandHandler;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler implements Runnable{
    private InfoSend infoSend;
    private int count;

    public ClientHandler(int count, Socket socket) throws IOException{
        this.infoSend = new InfoSend(socket);
        this.count = count;
    }

    @Override
    public void run() {
        try {
            while (!infoSend.isClosed()) {
                CommandHandler.makeCommand( infoSend.readMessage(), infoSend );
            }
            System.out.println(count + " Вышел");
            if(!infoSend.isClosed())
                infoSend.close();
        } catch (SocketException ex) {
            System.out.println(count + " Вышел(SocketExeprion)");
            ex.printStackTrace();
        } catch (EOFException ex) {
            System.err.println("Ошибка чтения сообщения из потока");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.err.println("Кливент " + count + "  неожиданно отключился");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Присланный объект не соответствует протоколу");
            ex.printStackTrace();
        }
    }

    /*public GroupSend getGroup(String name) {
        for (GroupSend group:
                Server.getGroupList()) {
            if(group.getNameGroup().equalsIgnoreCase(name)) {
                return group;
            }
        }
        return null;
    }*/

}
