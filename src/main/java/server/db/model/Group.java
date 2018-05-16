package server.db.model;

import commonData.MessageSend;
import org.hibernate.annotations.BatchSize;
import commonData.InfoSend;
import server.db.Factory;

import javax.persistence.*;
import java.io.IOException;
import java.util.*;

@Entity
@Table(name = "Groups")
@BatchSize(size = 30)
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private int id;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY, cascade =  CascadeType.REMOVE, orphanRemoval = true)
    private Set<Message> messageList = new HashSet<Message>();

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "group_user",
            joinColumns = {@JoinColumn(name = "id_group")},
            inverseJoinColumns = {@JoinColumn(name = "id_user")})
    private Set<User> userList = new HashSet<User>();

    @Transient
    private Set<InfoSend> onlineUsers = new HashSet<InfoSend>();

    //private Map<User, InfoSend> onlineUsers;

    public Group() {

    }

    public Group(String name, User user, InfoSend infoSend) {
        if(user != null)
            userList.add(user);

        this.name = name;
        if(infoSend != null)
            onlineUsers.add(infoSend);
    }

    public Group(String name) {
        this(name, null, null);
    }


    public void sendMssage(MessageSend messageSend) throws IOException {
        User user = null;
        for (User x : userList) {
            if (x.getName().equalsIgnoreCase(messageSend.getUser().getName())
                    && x.getPassword().equalsIgnoreCase(messageSend.getUser().getPassword())) {
                user = x;
            }
        }
        //если юзер принадлежит этой группе
        if (user == null)
            return;
        //Сохраняем сообщение в БД
        Message message = new Message(messageSend, user, this);
        Factory.getMessageService().add(message);
        //messageList.add(message);

        //Сообщяем всем о новом сообщении только
        for (InfoSend infoSend:
             onlineUsers) {
            infoSend.sendMessage(messageSend);
        }
    }


    public void addUser(User user, InfoSend infoSend) {
        userList.add(user);
        onlineUsers.add(infoSend);
    }

    public void removeUser(User user, InfoSend infoSend) {
        userList.remove(user);
        onlineUsers.remove(infoSend);
    }


    public Set<User> getUserList() {
        return userList;
    }

    public void addOnlineUser(InfoSend infoSend) {
        onlineUsers.add(infoSend);
    }

    public void removeOnlineUser(InfoSend infoSend) {
        onlineUsers.remove(infoSend);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(Set<Message> messageList) {
        this.messageList = messageList;
    }

    public void setUserList(Set<User> userList) {
        this.userList = userList;
    }


}
