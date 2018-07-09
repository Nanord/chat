package server.command.serverCommand.commandsList;

import commonData.Data;
import server.command.clientCommand.ClientCommandHandler;
import server.command.pattern.CommandHandler;

public class UpdateClientList implements ServerCommand{

    @Override
    public void make(String txt) {
        Data.reload(false);
        ClientCommandHandler.addComands();
        System.out.println("OK");
    }
}
