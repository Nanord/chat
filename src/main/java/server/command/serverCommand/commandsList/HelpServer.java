package server.command.serverCommand.commandsList;

import commonData.DATA;

import java.util.Map;
import java.util.stream.Stream;

public class HelpServer implements ServerCommand {
    @Override
    public String make(String txt) {
        StringBuilder str = new StringBuilder();

        str.append("Список комманд: \n");
        Stream<Map.Entry<String, String>> stream = DATA.getCommandServer();
        stream.forEach((x) -> {
            str.append("Command: " + x.getKey() + " Class: " + x.getValue() + ".java" + "\n");
        });

        return str.toString();
    }

}
