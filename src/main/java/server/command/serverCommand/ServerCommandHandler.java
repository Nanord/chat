package server.command.serverCommand;

import server.command.pattern.Command;
import server.command.pattern.CommandHandler;
import server.command.serverCommand.commandsList.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ServerCommandHandler extends CommandHandler{
    private static volatile ServerCommandHandler instance;

    private ServerCommandHandler() {
        addComands();
    }

    public static ServerCommandHandler getInstance() {
        if(instance == null) {
            synchronized (ServerCommandHandler.class) {
                if(instance == null)
                    instance = new ServerCommandHandler();
            }
        }
        return instance;
    }

    private static Map<String, Command> commandList;

    public void makeCommand() {
        try {
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String data;
            do {
                System.out.println("\nВведите комманду: ");
                data = keyboard.readLine();
                System.out.println(make(data));
            } while (true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String makeCommand(String txt) {
        try {
            return make(txt);
        } catch (Exception ex) {
            return ex.toString();
        }
    }

    private String make(String data) throws Exception{
        if (!data.isEmpty() && data.substring(0, 1).equalsIgnoreCase("/")) {
            String[] comm_text = data.split(" ");
            String comm = comm_text[0];
            String text = (comm_text.length > 1) ? comm_text[1] : null;

            ServerCommand serverCommand = (ServerCommand) commandList.get(comm);
            if (serverCommand != null) {
                try {
                    return serverCommand.make(text);
                } catch (Exception ex) {
                    return "Комманда \"" + comm + "\" не выполненна" + "\n";
                }
            } else {
                return "Неизвестная комманда" + "\n";
            }
        } else
            return "Некорректный ввод" + "\n";
    }


    public static void addComands() {
        commandList = Collections.synchronizedMap(new HashMap<String, Command>());
        addComands(commandList, true);
    }

}
