package server.command.clientCommand.commandsList;

import commonData.MessageSend;
import commonData.InfoSend;
import server.command.template.Command;

import java.io.IOException;

public interface ClientCommand extends Command {
    void make(MessageSend msg, InfoSend infoSend) throws IOException;
}
