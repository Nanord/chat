package server.command.pattern;

import commonData.Data;
import server.command.clientCommand.ClientCommandHandler;
import server.command.clientCommand.commandsList.ClientCommand;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class CommandHandler {
    protected static Map<String, Command> commandList = Collections.synchronizedMap(new HashMap<String, Command>());

    public static void addComands(boolean server) {
        HashMap<String, String> comm = server? Data.getCommandServer() : Data.getCommandClient();
        comm.entrySet().stream()
                .forEach(x -> {
                    findClass(x.getKey(), x.getValue(), server);
                });
    }

    protected static void findClass(String comm, String className, boolean server) {
        try {
            Command serverCommand = (Command) Class.forName("server.command." + (server? "serverCommand" : "clientCommand") + ".commandsList." + className).newInstance();
            commandList.put(comm, serverCommand);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
