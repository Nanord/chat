package server;

import data.Group;
import data.Message;
import data.Client;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class ClientHandler extends Thread{
    private Socket socket;
    private int count;
    private Client client;
    private Message message;

    public ClientHandler(int count, Socket socket) {
        this.socket = socket;
        this.count = count;
    }

    @Override
    public void run() {
        try {
            final ObjectInputStream inputStream = new ObjectInputStream(this.socket.getInputStream());
            final ObjectOutputStream outputStream = new ObjectOutputStream(this.socket.getOutputStream());

            this.message = (Message) inputStream.readObject();
            this.client = message.getClient();

            while (!socket.isClosed()) {
                this.message = (Message) inputStream.readObject();
                System.out.println("Клиент " + client.getId() + " пишет : " + message.getData());

                if(message.getData().substring(0,1).equalsIgnoreCase("-")) {
                    String[] comm_text = message.getData().split(" ");
                    String comm = comm_text[0];
                    String text = comm_text[1];
                    if (comm.equalsIgnoreCase("-q")) {
                        System.out.println("Клиент " + count + " отключился");
                        socket.close();
                    } else if (comm.equalsIgnoreCase("-join")){
                        Group group = getGroup(text);
                        group.addUser(client);
                        group.sendMssage(new Message(null, "Вошел в группу: " + client.getId()));
                    } else {

                    }
                }

            }

            inputStream.close();
            outputStream.close();
            socket.close();

        } catch (SocketException ex) {
            System.out.println(client.getId() + "Вышел");
        } catch (IOException ex) {
            System.err.println("Кливент " + count + "  неожиданно отключился");
        } catch (ClassNotFoundException ex) {
            System.out.println("Присланный объект не соответствует протоколу");
            ex.printStackTrace();
        }
    }

    public synchronized static Group getGroup(String name) {
        for (Group group:
                Server.getGroupList()) {
            if(group.getNameGroup().equalsIgnoreCase(name)) {
                return group;
            }
        }
        return null;
    }

}
