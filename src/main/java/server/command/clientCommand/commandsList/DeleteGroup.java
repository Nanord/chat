package server.command.clientCommand.commandsList;

import commonData.InfoSend;
import commonData.MessageSend;
import server.DataServer;
import server.subscription.EventManager;
import server.subscription.EventType;

import java.io.IOException;

public class DeleteGroup implements ClientCommand {
    private String comm;
    private String help;

    private EventManager eventManager = EventManager.getInstance();

    @Override
    public void make(MessageSend msg, InfoSend infoSend) throws IOException {
        if(!msg.getData().isEmpty() && DataServer.deleteGroup(msg.getData(), msg.getUser())) {
            infoSend.sendMessage(new MessageSend(
                    null,
                    msg.getCommandText(),
                    "ResponseServer: Готово, всё сломалось(",
                    "general"
            ));
            eventManager.notify(EventType.GROUP_DELETE, msg.getData());
        }
        else {
            infoSend.sendMessage(new MessageSend(
                    null,
                    "/error",
                    "ResponseServer: Не работает это(скорее всего имя группы не соответствует существующим)",
                    msg.getNameGroup()
            ));
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
