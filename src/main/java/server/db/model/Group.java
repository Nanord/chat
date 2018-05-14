package server.db.model;

import server.InfoSend;

import javax.persistence.*;
import java.io.IOException;
import java.util.*;


public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private int id;

    @Column(name = "messages")
    private List<Message> messageList;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "users")
    private Set<User> userList;

    private Set<InfoSend> onlineUsers ;

    //private Map<User, InfoSend> onlineUsers;

    public Group(String name, Set<User> userList) {
        if(userList != null)
            this.userList = userList;
        else
            this.userList = new HashSet<User>();

        this.name = name;
        this.messageList = new ArrayList<Message>();
        this.onlineUsers = new HashSet<InfoSend>();
    }

    public Group(String name) {
        this(name, null);
    }


    public void sendMssage(Message message) throws IOException {
        messageList.add(message);
        /*for (Map.Entry<User, InfoSend> user:
             onlineUsers.entrySet()) {
            //Чтобы самому себе не отправлять сообщения
            if(!user.getKey().equals(message.getUser())) {
                user.getValue().sendMessage(message);
            }
        }*/
        if(userList.contains(message.getUser()))
            for (InfoSend infoSend:
                 onlineUsers) {
                infoSend.sendMessage(message);
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

    public List<Message> getMessageList() {
        return messageList;
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
}
