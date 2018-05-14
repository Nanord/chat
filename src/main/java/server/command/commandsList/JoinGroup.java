package server.command.commandsList;

import server.db.model.Group;
import server.InfoSend;
import server.db.model.Message;
import server.Server;

import java.io.IOException;

public class JoinGroup implements Command{
    @Override
    public void make(Message msg, InfoSend infoSend) throws IOException {
        Server.getGroup(msg.getNameGroup()).removeUser(msg.getUser(), infoSend);

        Group group = Server.getGroup(msg.getData());
        if(group != null) {
          group.sendMssage(
                  new Message(
                          null,
                          msg.getCommandText(),
                          "ResponseServer: Поприветствуйте: " + msg.getUser().getName(),
                          group.getName()));
        } else {
            infoSend.sendMessage(
                    new Message(
                            null,
                            "/error",
                            "Группы с таким именем не существует"));
        }
    }
}
