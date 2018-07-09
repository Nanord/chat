package server.command.serverCommand.commandsList;

import server.command.pattern.Command;

public interface ServerCommand extends Command {
    String make(String txt);
}
