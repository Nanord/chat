package server.command.clientCommand.commandsList;

import commonData.MessageSend;
import server.DataServer;
import commonData.InfoSend;

import java.io.IOException;

public class CreateGroup implements ClientCommand {
    @Override
    public void make(MessageSend msg, InfoSend infoSend) throws IOException {
        if(msg.getData().isEmpty() || !DataServer.addGroup(msg.getData(), msg.getUser(), infoSend)) {
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
                            "ResponseServer: Всё ок",
                            msg.getData()));
        }
    }
}
