package server.subscription.eventListeners;

import commonData.InfoSend;
import commonData.MessageSend;

import java.io.IOException;

public class GroupCreatedListener implements EventListener {

    private InfoSend infoSend;
    public GroupCreatedListener(InfoSend infoSend) {
        this.infoSend = infoSend;
    }

    @Override
    public void update(Object... obj) {
        try {
            String groupName = (String) obj[0];
            String nameUser = (String) obj[1];
            if(!infoSend.isClosed())
                infoSend.sendMessage(new MessageSend(
                        null,
                        "/notify",
                        "ResponseServer: Группа \"" + groupName + "\" - создана юзером \"" + nameUser +"\"",
                        null
                ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
