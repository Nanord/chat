package server.command.commandsList;

import data.Message;
import server.InfoSend;

import java.io.IOException;

public interface Command {
    void make(Message msg, InfoSend infoSend) throws IOException;
    String getCommandText();
}
