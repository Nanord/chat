package data;

public class Message {
    private Client client;
    private String data;

    public Message(Client client, String data) {
        this.client = client;
        this.data = data;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
