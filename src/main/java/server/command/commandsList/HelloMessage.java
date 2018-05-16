package server.command.commandsList;

import commonData.MessageSend;
import commonData.UserSend;
import server.DataServer;
import server.db.model.Message;
import commonData.InfoSend;
import server.Server;

import java.io.IOException;

public class HelloMessage implements Command{

    @Override
    public void make(MessageSend msg, InfoSend infoSend) throws IOException {
        //Добовляем юзера
        UserSend newUser = DataServer.addNewUser(msg.getUser(), infoSend);
        //отправляем данные Юзеру о его id и стартовую группу
        infoSend.sendMessage(new MessageSend(newUser, msg.getCommandText(), "ResponseServer: Добро пожаловать", "general"));
    }
}
