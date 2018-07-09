package server.command.serverCommand.commandsList;

import server.Server;

import java.io.IOException;

public class ServerStop implements ServerCommand {
    @Override
    public void make(String txt) {
        try {
            boolean flag = Server.stop();
            String str = flag ? "Готово" : "Сервер уже выключен";
            System.out.println(str);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}