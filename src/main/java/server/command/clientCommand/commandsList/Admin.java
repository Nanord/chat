package server.command.clientCommand.commandsList;

import commonData.InfoSend;
import commonData.MessageSend;
import commonData.UserSend;
import server.command.serverCommand.ServerCommandHandler;
import server.command.serverCommand.commandsList.SetAdmin;

import java.io.IOException;

public class Admin implements ClientCommand {
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
}
