package server.command.clientCommand.commandsList;

import commonData.InfoSend;
import commonData.MessageSend;
import server.DataServer;

import java.io.IOException;

public class ListGroup implements ClientCommand {
    private String comm;
    private String help;

    @Override
    public void make(MessageSend msg, InfoSend infoSend) throws IOException {
        StringBuilder str = new StringBuilder();

        DataServer.getGroupMap().forEach((x, y) -> {
            str.append("Group Name: ");
            str.append("\"").append(x).append("\"\n\t");
            str.append("Users in group: \n");
            y.getOnlineUsers().forEach(o -> str.append("\t\t\"").append(o.getUserSend().getName()).append("\"\n"));
        });

        infoSend.sendMessage(new MessageSend(
                null,
                msg.getCommandText(),
                "ResponseServer: \n" + str.toString(),
                msg.getNameGroup()
        ));
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
