package commonData;

import server.db.model.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class InfoSend {
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public InfoSend(Socket socket) throws IOException{
        this.socket = socket;
        outputStream = new ObjectOutputStream(this.socket.getOutputStream());
        inputStream = new ObjectInputStream(this.socket.getInputStream());
    }

    public MessageSend readMessage() throws IOException, ClassNotFoundException {
        return (MessageSend) inputStream.readObject();
    }

    public void setInputStream(ObjectInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void sendMessage(MessageSend msg) throws IOException{
        outputStream.writeObject(msg);
        outputStream.flush();
    }

    public void setOutputStream(ObjectOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void close() throws IOException {
        inputStream.close();
        outputStream.close();
        socket.close();
    }

    public boolean isClosed() {
        return socket.isClosed();
    }
}