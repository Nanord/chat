package server;

import server.command.serverCommand.ServerCommandHandler;

public class Main {
    public static void main(String[] args) {
        DataServer.init();
        ServerCommandHandler.getInstance().makeCommand();
    }
}
