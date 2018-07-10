package server.command.clientCommand;

import commonData.MessageSend;
import commonData.InfoSend;
import server.command.clientCommand.commandsList.*;
import server.command.pattern.Command;
import server.command.pattern.CommandHandler;

import java.io.IOException;
import java.util.*;

public class ClientCommandHandler extends CommandHandler {
    private static volatile ClientCommandHandler instance;

    private ClientCommandHandler() {
        addComands();
    }

    public static ClientCommandHandler getInstance() {
        if(instance == null) {
            synchronized (ClientCommandHandler.class) {
                if(instance == null)
                    instance = new ClientCommandHandler();
            }
        }
        return instance;
    }

    private static Map<String, Command> commandList;

    public static void makeCommand(MessageSend msg, InfoSend infoSend) throws IOException {
        ClientCommand clientCommand = (ClientCommand) commandList.get(msg.getCommandText());
        if (clientCommand != null) {
            clientCommand.make(msg, infoSend);
        } else {
            infoSend.sendMessage(new MessageSend(
                    null,
                    "/error",
                    "ResponseServer: Неизвестная комманда",
                    msg.getNameGroup()));
        }
    }

    public static void addComands() {
        commandList = Collections.synchronizedMap(new HashMap<String, Command>());
        addComands(commandList, false);
    }
}
