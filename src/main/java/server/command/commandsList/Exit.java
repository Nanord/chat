package server.command.commandsList;

import data.Message;
import data.InfoSend;

import java.io.IOException;

public class Exit implements Command{
    @Override
    public void make(Message msg, InfoSend infoSend) throws IOException {
        infoSend.sendMessage(new Message(null, msg.getCommandText(), "ResponseServer: Пока("));
        infoSend.close();
    }
}
