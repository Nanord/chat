package server.command.clientCommand.commandsList;

import commonData.InfoSend;
import commonData.MessageSend;
import server.DataServer;

import java.io.IOException;

public class ListUser implements ClientCommand {
    @Override
    public void make(MessageSend msg, InfoSend infoSend) throws IOException {
        StringBuilder str = new StringBuilder();

        DataServer.getGroupMap().values().forEach( x -> {
            String nameGroup = x.getName();
            x.getOnlineUsers().forEach( y -> str.append( y.getUserSend().getName()).append(" - ").append(nameGroup));
        });

        infoSend.sendMessage(new MessageSend(
                null,
                msg.getCommandText(),
                "ResponseServer: \n" + str.toString(),
                msg.getNameGroup()
        ));
    }
}
