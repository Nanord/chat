package server.command.serverCommand.commandsList;

import commonData.DATA;

public class ReloadData implements ServerCommand {
    private String comm;
    private String help;


    @Override
    public String make(String txt) {
        StringBuilder str = new StringBuilder();
        str.append(DATA.reload(true));
        System.out.println("Готово!" + "\n");

        return str.toString();
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
