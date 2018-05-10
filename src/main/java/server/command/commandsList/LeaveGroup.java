package server.command.commandsList;

import server.InfoSend;
import server.db.model.Message;
import server.Server;

import java.io.IOException;

public class LeaveGroup implements Command{
    @Override
    public void make(Message msg, InfoSend infoSend) throws IOException {
        Server.getGroup(msg.getData()).removeUser(msg.getUser(), infoSend);
        Server.addUser(msg.getUser(), infoSend);
    }
}
