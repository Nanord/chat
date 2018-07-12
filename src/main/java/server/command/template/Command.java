package server.command.template;

public interface Command {
    void setComm(String comm);
    void setHelp(String help);
    String getComm();
    String getHelp();
}
