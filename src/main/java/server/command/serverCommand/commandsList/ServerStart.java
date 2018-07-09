package server.command.serverCommand.commandsList;

import commonData.Data;
import server.Server;

import java.net.UnknownHostException;

public class ServerStart extends Thread implements ServerCommand {

    @Override
    public void make(String txt) {
        Data.reload();
        if(Server.isStarted()) {
            System.out.println("Server is started");
        }
        else {
            super.start();
        }
    }

    @Override
    public void run() {
        try {
            Server server = new Server(Data.getPORT());
            server.startServer();
        } catch (UnknownHostException ex) {
            System.err.println("Неудалось узнать IP сервера");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
