package server.command.clientCommand.commandsList;

import commonData.MessageSend;
import server.DataServer;
import commonData.InfoSend;
import server.subscription.EventManager;
import server.subscription.EventType;

import java.io.IOException;

public class CreateGroup implements ClientCommand {
    private EventManager eventManager = EventManager.getInstance();

    @Override
    public void make(MessageSend msg, InfoSend infoSend) throws IOException {
        if(!DataServer.addGroup(msg.getData(), msg.getUser(), infoSend)) {
            infoSend.sendMessage(new MessageSend(
                    null,
                    "/error",
                    "ResponseServer: Группа с таким именем уже существует",
                    msg.getNameGroup()));
        } else {
            eventManager.notify(EventType.GROUP_CREATED, msg.getData(), msg.getUser().getName());
            infoSend.sendMessage(
                    new MessageSend(
                            null,
                            msg.getCommandText(),
                            "ResponseServer: Всё ок",
                            msg.getData()));
        }
    }
}
