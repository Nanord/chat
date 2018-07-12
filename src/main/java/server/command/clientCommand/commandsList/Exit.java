package server.command.clientCommand.commandsList;

import commonData.MessageSend;
import server.DataServer;
import commonData.InfoSend;

import java.io.IOException;

public class Exit implements ClientCommand {
    private String comm;
    private String help;

    @Override
    public void make(MessageSend msg, InfoSend infoSend) throws IOException {
        DataServer.exitOnlineUser(msg.getNameGroup(), infoSend);
        infoSend.sendMessage(new MessageSend(
                null, msg.getCommandText(),
                "ResponseServer: Пока(",
                null));
        infoSend.close();
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
