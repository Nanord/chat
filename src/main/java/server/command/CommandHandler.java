package server.command;

import data.Message;
import data.User;
import server.InfoSend;
import server.command.commandsList.Command;
import server.command.commandsList.Exit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandHandler {
    private static List<Command> commandList = Collections.synchronizedList(new ArrayList<Command>());

    public CommandHandler() {
        addComands();
    }


    public static void makeCommand(Message msg, InfoSend infoSend) throws IOException {
        boolean flag = false;
        for (Command command:
             commandList) {
            if(command.getCommandText().equalsIgnoreCase(msg.getCommandText())){
                command.make(msg, infoSend);
                flag = true;
                break;
            }
        }
        if(!flag)
            infoSend.sendMessage(new Message(null, "/error", "Неизвестная комманда"));
    }

    private void addComands() {
        commandList.add(new Exit());
    }


}
