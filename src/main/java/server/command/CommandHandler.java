package server.command;

import data.Message;
import data.InfoSend;
import server.command.commandsList.*;

import java.io.IOException;
import java.util.*;

public class CommandHandler {
    private static Map<String, Command> commandList = Collections.synchronizedMap(new HashMap<String, Command>());

    public CommandHandler() {
        addComands();
    }


    public static void makeCommand(Message msg, InfoSend infoSend) throws IOException {
        Command command = commandList.get(msg.getCommandText());
        if(command != null) {
            command.make(msg, infoSend);
        } else {
            infoSend.sendMessage(new Message(null, "/error", "Неизвестная комманда"));
        }
    }

    private void addComands() {
        commandList.put("/exit", new Exit());
        commandList.put("/send", new SendMessage());
        commandList.put("/createGroup", new CreateGroup());
        commandList.put("/serverHello", new HelloMessage());
    }


}
