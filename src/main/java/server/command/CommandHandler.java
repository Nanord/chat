package server.command;

import commonData.MessageSend;
import server.db.model.Message;
import commonData.InfoSend;
import server.command.commandsList.*;

import java.io.IOException;
import java.util.*;

public class CommandHandler {
    private static volatile CommandHandler instance;

    private CommandHandler() {
        addComands();
    }

    public static CommandHandler getInstance() {
        if(instance == null) {
            synchronized (CommandHandler.class) {
                if(instance == null)
                    instance = new CommandHandler();
            }
        }
        return instance;
    }


    private static Map<String, Command> commandList = Collections.synchronizedMap(new HashMap<String, Command>());

    public static void makeCommand(MessageSend msg, InfoSend infoSend) throws IOException {
        Command command = commandList.get(msg.getCommandText());
        if(command != null) {
            command.make(msg, infoSend);
        } else {
            infoSend.sendMessage(new MessageSend(
                    null,
                    "/error",
                    "Неизвестная комманда",
                    msg.getNameGroup()));
        }
    }

    private void addComands() {
        commandList.put("/exit", new Exit());
        commandList.put("/send", new SendMessage());
        commandList.put("/createGroup", new CreateGroup());
        commandList.put("/serverHello", new HelloMessage());
        commandList.put("/joinGroup", new JoinGroup());
    }


}
