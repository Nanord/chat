package server;

import commonData.InfoSend;
import server.command.clientCommand.ClientCommandHandler;
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
            while (!infoSend.isClosed()) {
                ClientCommandHandler.makeCommand(infoSend.readMessage(), infoSend);
            }
            System.out.println(count + " Вышел");

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
            if(infoSend != null)
                eventManager.notify(EventType.USERS_EXIT, infoSend.getUserSend().getName());
        }
    }

}
