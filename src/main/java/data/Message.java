package data;

import java.io.Serializable;
import java.util.Date;
import java.util.Timer;

public class Message implements Serializable {

    private User user;

    private String commandText;
    private String data;

    private String nameGroup;

    private String time;

    public Message(User user,String commandText, String data, String nameGroup) {
        this.user = user;
        this.commandText = commandText;
        this.data = data;
        this.nameGroup = nameGroup;

        time = new Date().toString();
    }

    public Message(User user, String commandText, String data) {
        this(user, commandText, data, null);
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

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }


    public String getCommandText() {
        return commandText;
    }

    public void setCommandText(String commandText) {
        this.commandText = commandText;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
