package server.command.clientCommand.commandsList;

import commonData.MessageSend;
import server.DataServer;
import commonData.InfoSend;

import java.io.IOException;

public class Exit implements ClientCommand {
    @Override
    public void make(MessageSend msg, InfoSend infoSend) throws IOException {
        DataServer.exitOnlineUser(msg.getNameGroup(), infoSend);
        infoSend.sendMessage(new MessageSend(
                null, msg.getCommandText(),
                "ResponseServer: Пока(",
                null));
        infoSend.close();
    }
}
