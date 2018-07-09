package server;

import commonData.Data;
import server.command.serverCommand.ServerCommandHandler;

public class Main {
    public static void main(String[] args) {
        ServerCommandHandler.getInstance().makeCommand();
    }
}
