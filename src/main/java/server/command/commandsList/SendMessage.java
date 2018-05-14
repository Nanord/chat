package server.command.commandsList;

import server.db.model.Message;
import server.InfoSend;
import server.Server;

import java.io.IOException;

public class SendMessage implements Command{

    @Override
    public void make(Message msg, InfoSend infoSend) throws IOException {
        if(!msg.getData().isEmpty())
            Server.getGroup(msg.getNameGroup()).sendMssage(msg);
    }
}
