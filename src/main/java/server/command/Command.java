package server.command;

public interface Command {

    void setText(String text);
    String getText();

    String  getCommand();
}
