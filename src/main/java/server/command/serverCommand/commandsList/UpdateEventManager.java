package server.command.serverCommand.commandsList;

import commonData.DATA;
import server.command.serverCommand.ServerCommandHandler;
import server.subscription.EventManager;

public class UpdateEventManager implements ServerCommand {
    @Override
    public String make(String txt) {
        EventManager.putListeners();
        return "OK" + "\n";
    }
}
