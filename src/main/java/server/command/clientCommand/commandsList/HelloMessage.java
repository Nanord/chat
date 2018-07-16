package server.command.clientCommand.commandsList;

import commonData.DATA;
import commonData.MessageSend;
import commonData.UserSend;
import server.DataServer;
import commonData.InfoSend;
import server.subscription.EventManager;
import server.subscription.EventType;
import server.subscription.eventListeners.UserEnteredListener;

import java.io.IOException;

public class HelloMessage implements ClientCommand {
    private String comm;
    private String help;

    private EventManager eventManager = EventManager.getInstance();

    @Override
    public void make(MessageSend msg, InfoSend infoSend) throws IOException {
        //Добовляем юзера
        UserSend newUser = DataServer.addNewUser(msg.getUser(), infoSend);
        //отправляем данные Юзеру о его id и стартовую группу
        if(newUser != null) {
            infoSend.sendMessage(new MessageSend(
                    newUser,
                    msg.getCommandText(),
                    "ResponseServer: Добро пожаловать",
                    DATA.getMainGroup()
            ));
            eventManager.notify(EventType.USERS_ENTERED, infoSend.getUserSend().getName());




        } else
            infoSend.sendMessage(new MessageSend(
                    null,
                    msg.getCommandText(),
                    "ResponseServe: Логин занят",
                    null
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
