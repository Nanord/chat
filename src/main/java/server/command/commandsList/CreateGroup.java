package server.command.commandsList;

import server.db.model.Group;
import server.db.model.Message;
import server.InfoSend;
import server.Server;

import java.io.IOException;

public class CreateGroup implements Command{

    @Override
    public void make(Message msg, InfoSend infoSend) throws IOException {
        Group group = new Group(msg.getData());
        group.addUser(msg.getUser(), infoSend);
        Server.addGroup(group);
        infoSend.sendMessage(new Message(null, msg.getCommandText(), "ResponseServer: group '" + msg.getData() + "' is created"));
    }
}
