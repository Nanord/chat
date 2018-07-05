package server.command.serverCommand.commandsList;

import server.Server;

import java.net.UnknownHostException;
import java.util.Scanner;

public class ServerStartConf implements ServerCommand {

    @Override
    public void make(String txt) {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Порт сервера: ");
            int port = in.nextInt();
            Server server = new Server(port);
            server.startServer();
        } catch (UnknownHostException ex) {
            System.out.println("Неудалось узнать IP сервера");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
