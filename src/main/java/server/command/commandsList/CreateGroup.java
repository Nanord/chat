package server.command.commandsList;

import data.Group;
import data.Message;
import data.InfoSend;
import data.User;
import server.Server;

import java.io.IOException;
import java.util.HashSet;

public class CreateGroup implements Command{

    @Override
    public void make(Message msg, InfoSend infoSend) throws IOException {
        Group group = new Group(msg.getData());
        group.addUser(msg.getUser(), infoSend);
        Server.addGroup(group);
        infoSend.sendMessage(new Message(null, msg.getCommandText(), "ResponseServer: group '" + msg.getData() + "' is created"));
    }
}
