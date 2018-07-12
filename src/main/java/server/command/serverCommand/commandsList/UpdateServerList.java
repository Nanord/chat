package server.command.serverCommand.commandsList;

import commonData.DATA;
import server.command.serverCommand.ServerCommandHandler;

public class UpdateServerList implements ServerCommand {
    private String comm;
    private String help;


    @Override
    public String make(String txt) {
        DATA.reload();
        ServerCommandHandler.addComands();
        return "OK" + "\n";
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
