package server.db;

import server.db.service.GroupService;
import server.db.service.MessageService;
import server.db.service.UserService;

public class Factory {
    private static UserService userService = null;
    private static GroupService groupService = null;
    private static MessageService messageService = null;

    private Factory() {
    }

    public static UserService getUserService() {
        if(userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public static GroupService getGroupService() {
        if(groupService == null) {
            groupService = new GroupService();
        }
        return groupService;
    }

    public static MessageService getMessageService() {
        if(messageService == null) {
            messageService = new MessageService();
        }
        return messageService;
    }


}
