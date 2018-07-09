package server.command.serverCommand.commandsList;

import commonData.Data;
import server.command.clientCommand.ClientCommandHandler;
import server.command.pattern.CommandHandler;
import server.command.serverCommand.ServerCommandHandler;

public class UpdateServerList implements ServerCommand {

    @Override
    public void make(String txt) {
        Data.reload();
        ServerCommandHandler.addComands();
        System.out.println("OK");
    }
}
