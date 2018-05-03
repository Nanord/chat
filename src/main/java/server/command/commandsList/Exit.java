package server.command.commandsList;

import server.command.Command;

public class Exit implements Command {
    private static String text = "-q";
    private String command;

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getCommand() {
        return command;
    }
}
