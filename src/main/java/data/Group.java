package data;

import java.io.IOException;
import java.util.List;

public class Group {
    private List<Message> messageList;
    private String nameGroup;
    private List<User> userList;

    public Group(List<User> userList, String nameGroup) {
        this.userList = userList;
        this.nameGroup = nameGroup;
    }


    public void sendMssage(Message message) throws IOException {
        for(User user :
                userList) {
            user.getOutputStream().writeObject(message);
        }
    }

    public void addMessage(Message message) {
        messageList.add(message);
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public List<User> getUserList() {
        return userList;
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
