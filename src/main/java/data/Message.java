package data;

import org.bson.types.ObjectId;

import java.io.Serializable;

public class Message implements Serializable {
    private ObjectId id;
    private User user;

    private String commandText;
    private String data;

    private Group group;

    public Message(User user,String commandText, String data) {
        this.user = user;
        this.commandText = commandText;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCommandText() {
        return commandText;
    }

    public void setCommandText(String commandText) {
        this.commandText = commandText;
    }
}
