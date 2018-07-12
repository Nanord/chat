package server.command.serverCommand.commandsList;

import commonData.InfoSend;
import commonData.UserSend;
import server.DataServer;
import server.db.model.Group;
import server.db.model.User;

public class RemoveUser implements ServerCommand {
    private String comm;
    private String help;

    @Override
    public String make(String txt) {
        StringBuilder str = new StringBuilder();

        str.append( DataServer.removeUser(txt) ? "OK" : "Ошибка");

        return str.toString();
    }

    @Override
    public String getComm() {
        return comm;
    }

    @Override
    public void setComm(String comm) {
        this.comm = comm;
    }

    @Override
    public String getHelp() {
        return help;
    }

    @Override
    public void setHelp(String help) {
        this.help = help;
    }
}
