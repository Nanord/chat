package server.command.commandsList;

import commonData.MessageSend;
import server.db.model.Message;
import commonData.InfoSend;

import java.io.IOException;

public interface Command {
    void make(MessageSend msg, InfoSend infoSend) throws IOException;
}
