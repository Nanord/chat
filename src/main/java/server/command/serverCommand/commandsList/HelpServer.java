package server.command.serverCommand.commandsList;

import commonData.CommandString;
import commonData.DATA;

import javax.xml.crypto.Data;
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
            str.append(DATA.ANSI_RED + "\"")
                    .append(x.getKey())
                    .append("\"" + DATA.ANSI_RESET + "Class: \"")
                    .append(x.getValue().getClassName())
                    .append(".java\"")
                    .append("\n")
                    .append("\t\"")
                    .append(x.getValue().getHelp())
                    .append("\"\n");
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
