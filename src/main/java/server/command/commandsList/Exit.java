package server.command.commandsList;

import server.db.model.Message;
import server.InfoSend;

import java.io.IOException;

public class Exit implements Command{
    @Override
    public void make(Message msg, InfoSend infoSend) throws IOException {
        infoSend.sendMessage(new Message(null, msg.getCommandText(), "ResponseServer: Пока("));
        infoSend.close();
    }
}
