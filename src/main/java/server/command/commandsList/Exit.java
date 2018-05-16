package server.command.commandsList;

import commonData.MessageSend;
import server.DataServer;
import server.db.model.Message;
import commonData.InfoSend;

import java.io.IOException;

public class Exit implements Command{
    @Override
    public void make(MessageSend msg, InfoSend infoSend) throws IOException {
        DataServer.exitOnlineUser(msg.getNameGroup(), infoSend);
        infoSend.sendMessage(new MessageSend(
                null, msg.getCommandText(),
                "ResponseServer: Пока(",
                null));
        infoSend.close();
    }
}
