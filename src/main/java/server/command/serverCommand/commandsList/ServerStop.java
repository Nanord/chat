package server.command.serverCommand.commandsList;

import server.Server;

import java.io.IOException;

public class ServerStop implements ServerCommand {
    @Override
    public String make(String txt) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            boolean flag = Server.stop();
            String str = flag ? "Готово" + "\n" : "Сервер уже выключен" + "\n";
            stringBuilder.append(str);

        } catch (IOException e) {
            stringBuilder.append(e.toString());
        }
        finally {
            return stringBuilder.toString();
        }
    }
}