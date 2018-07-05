package server.command.serverCommand;

import commonData.Data;
import server.command.pattern.CommandHandler;
import server.command.serverCommand.commandsList.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ServerCommandHandler extends CommandHandler implements Runnable {
    private static volatile ServerCommandHandler instance;

    private ServerCommandHandler() {
        addComands(true);
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

    @Override
    public void run() {
        try {
            System.out.println("Введите комманду: ");
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String data;
            while (true) {
                data = keyboard.readLine();
                System.out.println("Введите комманду: ");
                if (!data.isEmpty() && data.substring(0, 1).equalsIgnoreCase("/")) {
                    String[] comm_text = data.split(" ");
                    String comm = comm_text[0];
                    String text = (comm_text.length > 1) ? comm_text[1] : null;

                    ServerCommand serverCommand = (ServerCommand) commandList.get(comm);
                    if (serverCommand != null) {
                        try {
                            serverCommand.make(text);
                        } catch (Exception ex) {
                            System.out.println("Комманда \"" + comm + "\" не выполненна");
                            ex.printStackTrace();
                        }
                    } else {
                        System.out.println("Неизвестная комманда");
                    }
                }
                else
                    System.out.println("Некорректный ввод");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
