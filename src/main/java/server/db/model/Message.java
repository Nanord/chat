package server.db.model;

import commonData.MessageSend;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.hibernate.annotations.BatchSize;
import server.db.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "Message")
@BatchSize(size = 200)
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "commandText")
    private String commandText;

    @Column(name = "data")
    private String data;

    @ManyToOne()
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "time")
    private String time;

    public Message() {

    }

    public Message(MessageSend messageSend, User user, Group group) {
        this.user = user;
        this.commandText = messageSend.getCommandText();
        this.data = messageSend.getData();
        this.group = group;
        this.time = messageSend.getTime();
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
