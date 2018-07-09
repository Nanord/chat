package server.command.serverCommand.commandsList;

import commonData.DATA;

public class ReloadData implements ServerCommand {

    @Override
    public String make(String txt) {
        StringBuilder str = new StringBuilder();
        str.append(DATA.reload(true));
        System.out.println("Готово!" + "\n");

        return str.toString();
    }
}
