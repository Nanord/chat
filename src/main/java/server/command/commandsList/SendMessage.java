package server.command.commandsList;

import data.Message;
import data.InfoSend;
import server.Server;

import java.io.IOException;

public class SendMessage implements Command{

    @Override
    public void make(Message msg, InfoSend infoSend) throws IOException {
        Server.getMainGoup().sendMssage(msg);
    }
}
