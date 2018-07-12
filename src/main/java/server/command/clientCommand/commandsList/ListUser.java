package server.command.clientCommand.commandsList;

import commonData.InfoSend;
import commonData.MessageSend;
import server.DataServer;

import java.io.IOException;

public class ListUser implements ClientCommand {
    private String comm;
    private String help;

    @Override
    public void make(MessageSend msg, InfoSend infoSend) throws IOException {
        StringBuilder str = new StringBuilder();

        DataServer.getGroupMap().values().forEach( x -> {
            String nameGroup = x.getName();
            x.getOnlineUsers().forEach( y -> str.append( y.getUserSend().getName()).append(" - ").append(nameGroup));
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
