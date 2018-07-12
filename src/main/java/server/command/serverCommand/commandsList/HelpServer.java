package server.command.serverCommand.commandsList;

import commonData.CommandString;
import commonData.DATA;

import java.util.Map;
import java.util.stream.Stream;

public class HelpServer implements ServerCommand {
    private String comm;
    private String help;

    @Override
    public String make(String txt) {
        StringBuilder str = new StringBuilder();

        str.append("Список комманд: \n");
        Stream<Map.Entry<String, CommandString>> stream = DATA.getCommandServer();
        stream.forEach((x) -> {
            str.append("Command: " + x.getKey() + " Class: " + x.getValue().getClassName() + ".java" + "\n" +
            "\tHelp:" + x.getValue().getHelp());
        });

        return str.toString();
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
