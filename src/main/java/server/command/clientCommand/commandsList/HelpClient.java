package server.command.clientCommand.commandsList;

import commonData.DATA;
import commonData.InfoSend;
import commonData.MessageSend;

import java.io.IOException;

public class HelpClient implements ClientCommand{
    private String comm;
    private String help;

    @Override
    public void make(MessageSend msg, InfoSend infoSend) throws IOException {
        StringBuilder str = new StringBuilder();
        DATA.getCommandClient().forEach(x -> str.append(x.getKey()).append(" - ").append(x.getValue().getHelp()).append('\n'));
        infoSend.sendMessage(new MessageSend(
                null,
                msg.getCommandText(),
                "ResponseServer: " + str,
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
