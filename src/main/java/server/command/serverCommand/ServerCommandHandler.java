package server.command.serverCommand;

import commonData.Data;
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
                if (!data.isEmpty() && data.substring(0, 1).equalsIgnoreCase("/")) {
                    String[] comm_text = data.split(" ");
                    String comm = comm_text[0];
                    String text = (comm_text.length > 1) ? comm_text[1] : null;

                    ServerCommand serverCommand = (ServerCommand) commandList.get(comm);
                    if (serverCommand != null) {
                        try {
                            serverCommand.make(text);
                        } catch (Exception ex) {
                            System.err.println("Комманда \"" + comm + "\" не выполненна");
                            ex.printStackTrace();
                        }
                    } else {
                        System.err.println("Неизвестная комманда");
                    }
                } else
                    System.err.println("Некорректный ввод");
            } while (true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void addComands() {
        commandList = Collections.synchronizedMap(new HashMap<String, Command>());
        addComands(commandList, true);
    }

}
