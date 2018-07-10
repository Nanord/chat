package server.command.clientCommand.commandsList;

import commonData.DATA;
import commonData.InfoSend;
import commonData.MessageSend;

import java.io.IOException;

public class HelpClient implements ClientCommand{
    @Override
    public void make(MessageSend msg, InfoSend infoSend) throws IOException {
        StringBuilder str = new StringBuilder();
        DATA.getCommandClient().forEach(x -> str.append(x.getKey()).append(" - ").append(x.getValue()).append('\n'));
        infoSend.sendMessage(new MessageSend(
                null,
                msg.getCommandText(),
                "ResponseServer: " + str,
                "general"
        ));
    }
}
