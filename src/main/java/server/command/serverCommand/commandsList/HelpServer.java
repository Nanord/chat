package server.command.serverCommand.commandsList;

import commonData.Data;

import java.util.Map;

public class HelpServer implements ServerCommand {
    @Override
    public void make(String txt) {
        System.out.println("Список комманд: \n");
        Map<String, String> map = Data.getCommandServer();
        map.forEach((x,y) -> {
            System.out.println("Command: " + x + " Class: " + y + ".java");
        });
    }
}
