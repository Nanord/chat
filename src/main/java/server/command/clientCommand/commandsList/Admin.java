package server.command.clientCommand.commandsList;

import commonData.InfoSend;
import commonData.MessageSend;
import commonData.UserSend;
import server.command.serverCommand.ServerCommandHandler;
import server.command.serverCommand.commandsList.SetAdmin;

import java.io.IOException;

public class Admin implements ClientCommand {
    private String comm;
    private String help;

    @Override
    public void make(MessageSend msg, InfoSend infoSend) throws IOException {
        UserSend admin = msg.getUser();
        if(SetAdmin.equalsAdmin(admin)) {
            String comm = msg.getData();
            String answer = ServerCommandHandler.getInstance().makeCommand(comm);
            infoSend.sendMessage(new MessageSend(
                    null,
                    msg.getCommandText(),
                    "ResponseServer: " + answer + "\n",
                    msg.getNameGroup()));
        }
        else {
            infoSend.sendMessage(new MessageSend(
                    null,
                    "/error",
                    "ResponseServer: Неизвестная комманда",
                    msg.getNameGroup()));
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
