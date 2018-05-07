package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Group {
    private List<Message> messageList;
    private String nameGroup;
    private Set<User> userList;
    private Set<InfoSend> onlineUsers;

    public Group(Set<User> userList, String nameGroup) {
        if(userList != null)
            this.userList = userList;
        else
            this.userList = new HashSet<User>();

        this.nameGroup = nameGroup;
        this.messageList = new ArrayList<Message>();
        this.onlineUsers = new HashSet<InfoSend>();
    }


    public void sendMssage(Message message) throws IOException {
        messageList.add(message);
        for (InfoSend user :
                onlineUsers) {
            user.sendMessage(message);
        }

    }

    public void addUser(User user, InfoSend infoSend) {
        userList.add(user);
        onlineUsers.add(infoSend);
    }

    public void removeUser(User user) {
        userList.remove(user);
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
