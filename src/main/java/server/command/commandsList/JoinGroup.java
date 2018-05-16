package server.command.commandsList;

import commonData.MessageSend;
import server.DataServer;
import server.db.model.Group;
import commonData.InfoSend;
import server.db.model.Message;
import server.Server;

import java.io.IOException;

public class JoinGroup implements Command{
    @Override
    public void make(MessageSend msg, InfoSend infoSend) throws IOException {
        DataServer.exitOnlineUser(msg.getNameGroup(), infoSend);
        boolean flag = DataServer.addUserInGroup(msg.getData(), msg.getUser(), infoSend);
        if(!flag) {
            infoSend.sendMessage(
                    new MessageSend(
                            null,
                            "/error",
                            "Группы с таким именем не существует",
                            msg.getNameGroup()));
        }
    }
}
