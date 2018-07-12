package server.command.clientCommand.commandsList;

import commonData.MessageSend;
import server.DataServer;
import commonData.InfoSend;

import java.io.IOException;

public class SendMessage implements ClientCommand {
    private String comm;
    private String help;


    @Override
    public void make(MessageSend msg, InfoSend infoSend) throws IOException {
        if(msg.getData() != null)
            if(!msg.getData().isEmpty())
                DataServer.getGroup(msg.getNameGroup()).sendMssage(msg);
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
