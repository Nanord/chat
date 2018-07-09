package server.command.serverCommand.commandsList;

import commonData.DATA;
import server.command.serverCommand.ServerCommandHandler;

public class UpdateServerList implements ServerCommand {

    @Override
    public String make(String txt) {
        DATA.reload();
        ServerCommandHandler.addComands();
        return "OK" + "\n";
    }
}
