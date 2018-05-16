package server.command.commandsList;

import commonData.InfoSend;
import commonData.MessageSend;
import server.DataServer;
import server.db.model.Message;

import java.io.IOException;

public class DeleteGroup implements Command {
    @Override
    public void make(MessageSend msg, InfoSend infoSend) throws IOException {
        if(DataServer.deleteGroup(msg.getData(), msg.getUser())) {
            infoSend.sendMessage(new MessageSend(
                    null,
                    msg.getCommandText(),
                    "ResponseServer: Готово, всё сломалось(",
                    "general"
            ));
        }
        else {
            infoSend.sendMessage(new MessageSend(
                    null,
                    "/error",
                    "ResponseServer: Не работает это(скорее всего имя группы не соответствует существующим)",
                    msg.getNameGroup()
            ));
        }
    }
}
