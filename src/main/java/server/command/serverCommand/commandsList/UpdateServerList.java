package server.command.serverCommand.commandsList;

import commonData.Data;
import server.command.pattern.CommandHandler;

public class UpdateServerList implements ServerCommand {

    @Override
    public void make(String txt) {
        Data.reload();
        CommandHandler.addComands(true);
        System.out.println("OK");
    }
}
