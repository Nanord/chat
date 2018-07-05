package server.command.clientCommand.commandsList;

import commonData.MessageSend;
import server.DataServer;
import commonData.InfoSend;

import java.io.IOException;

public class SendMessage implements ClientCommand {

    @Override
    public void make(MessageSend msg, InfoSend infoSend) throws IOException {
        if(msg.getData() != null)
            if(!msg.getData().isEmpty())
                DataServer.getGroup(msg.getNameGroup()).sendMssage(msg);
    }
}
