package server.command.pattern;

import commonData.DATA;
import java.util.Map;
import java.util.stream.Stream;

public abstract class CommandHandler {

    protected static void addComands(Map<String, Command> commandList, boolean server) {
        Stream<Map.Entry<String, String>> comm = server ? DATA.getCommandServer() : DATA.getCommandClient();

        comm.forEach((data) -> {
                String strCommand = data.getKey();
                Command command = findClass(data.getValue(), server);
                commandList.put(strCommand, command);
            });


        String key;

    }

    private static Command findClass(String className, boolean server) {
        Command command = null;
        try {
            command =(Command) Class.forName("server.command." + (server ? "serverCommand" : "clientCommand") + ".commandsList." + className).newInstance();
        } catch (ClassNotFoundException e) {
            System.out.println(className + ".java не найден");
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return command;
    }


}
