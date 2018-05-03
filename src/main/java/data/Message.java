package data;

import java.io.Serializable;

public class Message implements Serializable {
    private User user;
    private String data;

    public Message(User user, String data) {
        this.user = user;
        this.data = data;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
