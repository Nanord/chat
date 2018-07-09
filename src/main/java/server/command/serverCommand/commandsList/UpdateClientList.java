package server.command.serverCommand.commandsList;

import commonData.DATA;
import server.command.clientCommand.ClientCommandHandler;

public class UpdateClientList implements ServerCommand{

    @Override
    public String make(String txt) {
        DATA.reload(false);
        ClientCommandHandler.addComands();
        return "OK" + "\n";
    }
}
