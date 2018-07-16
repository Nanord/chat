package server.command.template;

import commonData.CommandString;
import commonData.DATA;
import java.util.Map;
import java.util.stream.Stream;

public abstract class CommandHandler {

    protected static void addComands(Map<String, Command> commandList, boolean server) {
        Stream<Map.Entry<String, CommandString>> comm = server ? DATA.getCommandServer() : DATA.getCommandClient();

        comm.forEach((data) -> {
                String strCommand = data.getKey();
                Command command = findClass(data.getValue(), server);
                commandList.put(strCommand, command);
            });
    }

    private static Command findClass(CommandString commandString, boolean server) {
        Command command = null;
        try {
            command =(Command) Class.forName("server.command." + (server ? "serverCommand" : "clientCommand") + ".commandsList." + commandString.getClassName()).newInstance();

        } catch (ClassNotFoundException e) {
            System.out.println(commandString.getClassName() + ".java не найден");
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return command;
    }

}
