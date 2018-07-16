package server.subscription.eventListeners;

import commonData.InfoSend;
import commonData.MessageSend;
import server.subscription.EventManager;
import server.subscription.EventType;

import java.io.IOException;

public class UserEnteredListener implements EventListener {

    private InfoSend infoSend;

    public UserEnteredListener(InfoSend infoSend) {
        this.infoSend = infoSend;
    }

    @Override
    public void update(Object... obj) {
        try {
            String nameUser = (String) obj[0];
            if(!infoSend.isClosed())
                infoSend.sendMessage(new MessageSend(
                        null,
                        "/notify",
                        "ResponseServer: " + nameUser + " - вошел",
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
