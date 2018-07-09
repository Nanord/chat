package server.command.serverCommand.commandsList;

import commonData.Data;

import java.util.Map;
import java.util.stream.Stream;

public class HelpServer implements ServerCommand {
    @Override
    public void make(String txt) {
        System.out.println("Список комманд: \n");
        Stream<Map.Entry<String, String>> stream = Data.getCommandServer();
        stream.forEach((x) -> {
            System.out.println("Command: " + x.getKey() + " Class: " + x.getValue() + ".java");
        });
    }

}
