package server.command.clientCommand.commandsList;

import commonData.MessageSend;
import server.DataServer;
import commonData.InfoSend;

import java.io.IOException;

public class JoinGroup implements ClientCommand {
    @Override
    public void make(MessageSend msg, InfoSend infoSend) throws IOException {
        DataServer.exitOnlineUser(msg.getNameGroup(), infoSend);
        boolean flag = DataServer.addUserInGroup(msg.getData(), msg.getUser(), infoSend);
        if(!flag) {
            infoSend.sendMessage(
                    new MessageSend(
                            null,
                            "/error",
                            "ResponseServer: Группы с таким именем не существует",
                            msg.getNameGroup()));
        } else {
            infoSend.sendMessage(
                    new MessageSend(
                            null,
                            msg.getCommandText(),
                            "ResponseServer: OK",
                            msg.getData()
                    )
            );
        }
    }
}
