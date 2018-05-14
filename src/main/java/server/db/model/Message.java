package server.db.model;

import server.db.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "User")
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private int id;

    @Column(name = "user", nullable = false)
    private User user;

    @Column(name = "commandText")
    private String commandText;

    @Column(name = "data")
    private String data;

    @Column(name = "nameGroup")
    private String nameGroup;

    @Column(name = "time")
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
