package server.command.serverCommand.commandsList;

import server.Server;

import java.io.IOException;

public class ServerStop implements ServerCommand {
    private String comm;
    private String help;

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

    @Override
    public String getComm() {
        return comm;
    }

    @Override
    public void setComm(String comm) {
        this.comm = comm;
    }

    @Override
    public String getHelp() {
        return help;
    }

    @Override
    public void setHelp(String help) {
        this.help = help;
    }
}