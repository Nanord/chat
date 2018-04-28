package server;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread{
    private Socket socket;
    private int count;

    public ClientHandler(int count, Socket socket) {
        this.socket = socket;
        this.count = count;
    }

    @Override
    public void run() {
        try {
            System.out.println("Подключился клиент " + count + ":"  + socket.getInetAddress().getHostAddress());

            InputStream sIn = socket.getInputStream();
            OutputStream sOut = socket.getOutputStream();

            DataInputStream in = new DataInputStream(sIn);
            DataOutputStream out = new DataOutputStream(sOut);

            String message = null;
            while (!socket.isClosed()) {
                message = in.readUTF();
                System.out.println("Клиент " + count + " пишет : " + message);

                if(message.equalsIgnoreCase("-q")) {
                    System.out.println("Клиент " + count + " отключился");
                    socket.close();
                } else {
                    out.writeUTF(message);
                    out.flush();
                }
            }

            in.close();
            out.close();
            socket.close();

        } catch (IOException ex) {
            System.err.println("Кливент " + count + "  неожиданно отключился");
        }
    }
}
