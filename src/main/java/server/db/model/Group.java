package server.db.model;

import server.InfoSend;

import java.io.IOException;
import java.util.*;


public class Group {
    private List<Message> messageList;
    private String nameGroup;
    private Set<User> userList;
    private Set<InfoSend> onlineUsers ;

    //private Map<User, InfoSend> onlineUsers;

    public Group(String nameGroup, Set<User> userList) {
        if(userList != null)
            this.userList = userList;
        else
            this.userList = new HashSet<User>();

        this.nameGroup = nameGroup;
        this.messageList = new ArrayList<Message>();
        this.onlineUsers = new HashSet<InfoSend>();
    }

    public Group(String nameGroup) {
        this(nameGroup, null);
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

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }
}
