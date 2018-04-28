package data;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class Group {
    private List<Message> messageList;
    private String nameGroup;
    private List<Client> clientList;

    public Group(List<Client> clientList, String nameGroup) {
        this.clientList = clientList;
        this.nameGroup = nameGroup;
    }


    public void sendMssage(Message message) throws IOException {
        for(Client client :
                clientList) {
            client.getOutputStream().writeObject(message);
        }
    }

    public void addMessage(Message message) {
        messageList.add(message);
    }

    public void addUser(Client client) {
        clientList.add(client);
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }
}
