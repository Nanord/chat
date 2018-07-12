package commonData;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


public class MessageSend implements Serializable {
    private UserSend user;

    private String commandText;

    private String data;

    private String nameGroup;

    private String time;

    public MessageSend(UserSend user, String commandText, String data, String nameGroup) {
        this.user = user;
        this.commandText = commandText;
        this.data = data;
        this.nameGroup = nameGroup;

        time = new Date().toString();
    }

    public MessageSend(String comm, String txt) {

    }


    public UserSend getUser() {
        return user;
    }

    public void setUser(UserSend user) {
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
