package server.command.clientCommand.commandsList;

import commonData.InfoSend;
import commonData.MessageSend;
import server.subscription.EventManager;
import server.subscription.EventType;
import server.subscription.eventListeners.GroupCreatedListener;
import server.subscription.eventListeners.UserEnteredListener;
import server.subscription.eventListeners.UsersExitListener;

import java.io.IOException;

public class SubscribeNotifications implements ClientCommand{
    private String comm;
    private String help;


    @Override
    public void make(MessageSend msg, InfoSend infoSend) throws IOException {
        EventManager eventManager = EventManager.getInstance();

        eventManager.subscribe(EventType.USERS_EXIT, new UsersExitListener(infoSend));
        eventManager.subscribe(EventType.USERS_ENTERED, new UserEnteredListener(infoSend));
        eventManager.subscribe(EventType.GROUP_CREATED, new GroupCreatedListener(infoSend));

        infoSend.sendMessage(new MessageSend(
                null,
                msg.getCommandText(),
                "ResponseServer: OK",
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
