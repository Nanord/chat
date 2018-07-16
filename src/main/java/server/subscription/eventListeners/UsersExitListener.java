package server.subscription.eventListeners;

import commonData.InfoSend;
import commonData.MessageSend;
import server.subscription.EventType;

import java.io.IOException;

public class UsersExitListener implements EventListener {

    private InfoSend infoSend;

    public UsersExitListener(InfoSend infoSend) {
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
                        "ResponseServer: " + nameUser + " вышел",
                        null
                ));
            else {
                infoSend.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public InfoSend getInfoSend() {
        return infoSend;
    }

    public void setInfoSend(InfoSend infoSend) {
        this.infoSend = infoSend;
    }
}
