package server.subscription.eventListeners;

import commonData.InfoSend;
import commonData.MessageSend;

import java.io.IOException;

public class GroupDeletedListener implements EventListener{

    private InfoSend infoSend;

    public GroupDeletedListener(InfoSend infoSend) {
        this.infoSend = infoSend;
    }

    @Override
    public void update(Object... obj) {
        try {
            String groupName = (String) obj[0];
            if(!infoSend.isClosed())
                infoSend.sendMessage(new MessageSend(
                        null,
                        "/notify",
                        "ResponseServer: Группа \"" + groupName + "\" - удалена",
                        null
                ));
            else {
                infoSend.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
