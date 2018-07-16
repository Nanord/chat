package server.command.clientCommand.commandsList;

import commonData.InfoSend;
import commonData.MessageSend;
import commonData.UserSend;
import server.DataServer;
import server.db.model.Message;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class GetMessages implements ClientCommand {
    private String comm;
    private String help;

    @Override
    public void make(MessageSend msg, InfoSend infoSend) throws IOException {
        Set<Message> messageList = DataServer.getGroup(msg.getNameGroup()).getMessageList();
        int j = (messageList.size() > 20?20:messageList.size());
        UserSend userSend =null;
        for (Message m:
             messageList) {
            userSend = new UserSend(m.getUser().getName(), null);
            infoSend.sendMessage(new MessageSend(
                    userSend,
                    msg.getCommandText(),
                    m.getData() ,
                    msg.getNameGroup(),
                    m.getTime()
            ));
            if(j < 0)
                break;
            else
                j--;
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
