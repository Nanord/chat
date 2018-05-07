package server.command.commandsList;

import data.Message;
import data.InfoSend;
import server.Server;

import java.io.IOException;

public class HelloMessage implements Command{

    @Override
    public void make(Message msg, InfoSend infoSend) throws IOException {
        //Добовляем юзера
        Server.addUser(msg.getUser(), infoSend);
        infoSend.sendMessage(new Message(null, msg.getCommandText(), "ResponseServer: Добро пожаловать"));
    }
}
