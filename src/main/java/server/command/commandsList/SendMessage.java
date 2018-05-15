package server.command.commandsList;

import commonData.MessageSend;
import server.DataServer;
import server.db.model.Message;
import commonData.InfoSend;
import server.Server;

import java.io.IOException;

public class SendMessage implements Command{

    @Override
    public void make(MessageSend msg, InfoSend infoSend) throws IOException {
        if(!msg.getData().isEmpty())
            DataServer.getGroup(msg.getNameGroup()).sendMssage(msg);
    }
}
