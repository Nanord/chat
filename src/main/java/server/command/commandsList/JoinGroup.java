package server.command.commandsList;

import server.db.model.Group;
import server.InfoSend;
import server.db.model.Message;
import server.Server;

import java.io.IOException;

public class JoinGroup implements Command{
    @Override
    public void make(Message msg, InfoSend infoSend) throws IOException {
        Server.getMainGoup().removeUser(msg.getUser(), infoSend);

        Group group = Server.joinGroup(msg.getData());
        if(group != null) {
          group.sendMssage(
                  new Message(
                          null,
                          msg.getCommandText(),
                          "ResponseServer: Поприветствуйте: " + msg.getUser().getName(),
                          group.getNameGroup()));
        } else {
            infoSend.sendMessage(
                    new Message(
                            null,
                            "/error",
                            "Группы с таким именем не существует"));
        }
    }
}
