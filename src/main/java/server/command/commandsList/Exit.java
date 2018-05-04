package server.command.commandsList;

import data.Message;
import server.InfoSend;

import java.io.IOException;

public class Exit implements Command{
    private final static String commandText = "/exit";

    @Override
    public void make(Message msg, InfoSend infoSend) throws IOException {
        infoSend.sendMessage(new Message(null, "/exit", "Пока("));
        infoSend.getSocket().close();
    }

    public String getCommandText() {
        return commandText;
    }
}
