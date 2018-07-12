package server.command.serverCommand.commandsList;

import server.command.template.Command;

public interface ServerCommand extends Command {
    String make(String txt);
}
