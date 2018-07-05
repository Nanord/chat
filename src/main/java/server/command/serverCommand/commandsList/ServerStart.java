package server.command.serverCommand.commandsList;

import server.Server;

import java.net.UnknownHostException;

public class ServerStart implements ServerCommand {

    @Override
    public void make(String txt) {
        try {
            Server server = new Server(/*Data.getPORT()*/1);
            server.startServer();
        } catch (UnknownHostException ex) {
            System.out.println("Неудалось узнать IP сервера");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
