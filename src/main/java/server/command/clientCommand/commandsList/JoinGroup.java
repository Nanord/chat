package server.command.clientCommand.commandsList;

import commonData.MessageSend;
import server.DataServer;
import commonData.InfoSend;

import java.io.IOException;

public class JoinGroup implements ClientCommand {
    private String comm;
    private String help;

    @Override
    public void make(MessageSend msg, InfoSend infoSend) throws IOException {
        if(msg.getNameGroup().equalsIgnoreCase(msg.getData())) {
            infoSend.sendMessage(
                    new MessageSend(
                            null,
                            "/error",
                            "ResponseServer: Вы уже в этой группе",
                            msg.getNameGroup()));
        }
        boolean flag = DataServer.addUserInGroup(msg.getData(), msg.getUser(), infoSend);
        if(!flag) {
            infoSend.sendMessage(
                    new MessageSend(
                            null,
                            "/error",
                            "ResponseServer: Группы с таким именем не существует",
                            msg.getNameGroup()));
        } else {
            DataServer.exitOnlineUser(msg.getNameGroup(), infoSend);
            infoSend.sendMessage(
                    new MessageSend(
                            null,
                            msg.getCommandText(),
                            "ResponseServer: OK",
                            msg.getData()
                    )
            );
        }
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
