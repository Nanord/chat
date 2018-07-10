package server.command.serverCommand.commandsList;

import commonData.DATA;
import server.Server;
import sun.awt.SunToolkit;

import java.net.SocketException;
import java.net.UnknownHostException;

public class ServerStart extends Thread implements ServerCommand {

    @Override
    public String make(String txt) {
        StringBuilder str = new StringBuilder();
        try {

            DATA.reload();
            if (Server.isStarted()) {
                str.append("Server is started" + "\n");
            } else {
                super.setName("server");
                //super.setDaemon(true);
                super.start();
                str.append("OK" + "\n");
            }

        } catch (SunToolkit.IllegalThreadException ex) {
            str.append(ex.toString());
        }
        return str.toString();
    }

    @Override
    public void run() {
        try {
            Server server = new Server(DATA.getPORT());
            server.startServer();
        } catch(InterruptedException ie){
            Thread.currentThread().interrupt();
        } catch (UnknownHostException ex) {
            System.err.println("Неудалось узнать IP сервера");
        } catch (SocketException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
