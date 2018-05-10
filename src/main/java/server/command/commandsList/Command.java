package server.command.commandsList;

import server.db.model.Message;
import server.InfoSend;

import java.io.IOException;

public interface Command {
    void make(Message msg, InfoSend infoSend) throws IOException;
}
