package server.command.clientCommand.commandsList;

import commonData.InfoSend;
import commonData.MessageSend;
import server.DataServer;

import java.io.IOException;

public class ListGroup implements ClientCommand {
    @Override
    public void make(MessageSend msg, InfoSend infoSend) throws IOException {
        StringBuilder str = new StringBuilder();

        DataServer.getGroupMap().forEach((x, y) -> {
            str.append("Group Name: ");
            str.append("\"").append(x).append("\"\n");
            str.append("Users in group: \n");
            y.getOnlineUsers().forEach(o -> str.append("\t\"").append(o.getUserSend().getName()).append("\"\n"));
        });

        infoSend.sendMessage(new MessageSend(
                null,
                msg.getCommandText(),
                "ResponseServer: \n" + str.toString(),
                msg.getNameGroup()
        ));
    }
}
