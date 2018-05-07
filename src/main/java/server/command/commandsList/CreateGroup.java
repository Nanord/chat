package server.command.commandsList;

import data.Message;
import data.InfoSend;

import java.io.IOException;

public class CreateGroup implements Command{

    @Override
    public void make(Message msg, InfoSend infoSend) throws IOException {
        msg.getData();
    }
}
