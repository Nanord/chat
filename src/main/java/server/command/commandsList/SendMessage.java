package server.command.commandsList;

import data.Message;
import server.InfoSend;

import java.io.IOException;

public class SendMessage implements Command{
    private static String commandText = "/send";

    @Override
    public void make(Message msg, InfoSend infoSend) throws IOException {
        infoSend.sendMessage(msg);
    }

    @Override
    public String getCommandText() {
        return commandText;
    }
}
