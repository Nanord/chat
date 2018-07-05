package server.command.serverCommand.commandsList;

import server.command.pattern.Command;

public interface ServerCommand extends Command {
    void make(String txt);
}
