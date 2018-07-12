package server.command.serverCommand.commandsList;

import commonData.InfoSend;
import commonData.UserSend;
import server.DataServer;
import server.db.model.Group;
import server.db.model.User;

public class RemoveUser implements ServerCommand {
    @Override
    public String make(String txt) {
        StringBuilder str = new StringBuilder();

        str.append( DataServer.removeUser(txt) ? "OK" : "Ошибка");

        return str.toString();
    }
}
