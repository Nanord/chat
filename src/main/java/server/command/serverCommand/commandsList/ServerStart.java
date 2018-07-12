package server.command.serverCommand.commandsList;

import commonData.DATA;
import server.Server;
import sun.awt.SunToolkit;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ServerStart implements ServerCommand, Runnable {
    @Override
    public String make(String txt) {
        StringBuilder str = new StringBuilder();
        try {

            DATA.reload();
            if (Server.isStarted()) {
                str.append("Server is started" + "\n");
            } else {
                Thread thread = new Thread(this, "server");
                //super.setDaemon(true);
                thread.start();
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
