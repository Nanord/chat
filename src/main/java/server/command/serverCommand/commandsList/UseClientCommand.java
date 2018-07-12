package server.command.serverCommand.commandsList;

import commonData.DATA;
import commonData.InfoSend;
import commonData.MessageSend;
import commonData.UserSend;
import server.command.clientCommand.ClientCommandHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class UseClientCommand implements ServerCommand{

    private static InfoSend infoSend;

    @Override
    public String make(String data) {
        StringBuilder str = new StringBuilder();

        try {
            MessageSend message = null;
            UserSend user = new UserSend("Admin", "123");
            String nameGroup = null;
            boolean flag = true;
            if (!data.isEmpty() && data.substring(0, 1).equalsIgnoreCase("/")) {
                String[] comm_text = data.split(" ");
                String comm = comm_text[0];
                int i = 1;
                if(comm.equalsIgnoreCase("/send")) {
                    nameGroup = comm_text[i++];
                    flag = false;
                }
                StringBuilder text = new StringBuilder();
                for (; i < comm_text.length; i++) {
                    if(i == comm_text.length - 1)
                        text.append(comm_text[i]);
                    else
                        text.append(comm_text[i] + " ");
                }
                message = new MessageSend(user, comm, text.toString(), nameGroup);
            }
            if(infoSend == null)
                infoSend = new InfoSend(new Socket("localhost", DATA.getPORT()));

            infoSend.sendMessage(message);
            str.append(flag? infoSend.readMessage().getData() : "OK");

        } catch (IOException | ClassNotFoundException e) {
           str.append(e.toString());
        }
        return str.toString();
    }
}
