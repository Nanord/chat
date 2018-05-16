package server.command.commandsList;

import commonData.MessageSend;
import server.DataServer;
import server.db.model.Group;
import server.db.model.Message;
import commonData.InfoSend;
import server.Server;

import java.io.IOException;

public class CreateGroup implements Command{
    @Override
    public void make(MessageSend msg, InfoSend infoSend) throws IOException {
        if(!DataServer.addGroup(msg.getData(), msg.getUser(), infoSend)) {
            infoSend.sendMessage(new MessageSend(
                    null,
                    "/error",
                    "ResponseServer: Группа с таким именем уже существует",
                    msg.getNameGroup()));
        } else {
            infoSend.sendMessage(
                    new MessageSend(
                            null,
                            msg.getCommandText(),
                            "Всё ок",
                            msg.getData()));
        }
    }
}
