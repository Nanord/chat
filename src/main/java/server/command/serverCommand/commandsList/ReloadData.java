package server.command.serverCommand.commandsList;

import commonData.Data;

public class ReloadData implements ServerCommand {

    @Override
    public void make(String txt) {
        Data.reload(true);
        System.out.println("Готово!");
    }
}
