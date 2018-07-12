package server.command.clientCommand.commandsList;

import commonData.InfoSend;
import commonData.MessageSend;
import server.subscription.EventManager;
import server.subscription.EventType;
import server.subscription.eventListeners.UsersExitListener;

import java.io.IOException;

public class SubscribeNotifications implements ClientCommand{

    @Override
    public void make(MessageSend msg, InfoSend infoSend) throws IOException {
        EventManager.getInstance().subscribe(EventType.USERS_EXIT, new UsersExitListener(infoSend));
        infoSend.sendMessage(new MessageSend(
                null,
                msg.getCommandText(),
                "ResponseServer: OK",
                msg.getNameGroup()
        ));
    }
}
