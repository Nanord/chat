package server.command.serverCommand.commandsList;

import commonData.Data;
import server.command.pattern.CommandHandler;

public class UpdateClientList implements ServerCommand{

    @Override
    public void make(String txt) {
        Data.reload(false);
        CommandHandler.addComands(false);
        System.out.println("OK");
    }
}
