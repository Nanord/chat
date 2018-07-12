package server.command.serverCommand.commandsList;

import commonData.DATA;
import server.Server;
import sun.awt.SunToolkit;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerStart implements ServerCommand, Runnable {
    private String comm;
    private String help;


    private ExecutorService executorService;

    public ServerStart() {
        this.executorService = Executors.newSingleThreadExecutor();
    }
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
                executorService.submit(thread);
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
