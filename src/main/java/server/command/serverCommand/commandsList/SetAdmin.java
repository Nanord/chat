package server.command.serverCommand.commandsList;

import commonData.UserSend;
import server.DataServer;
import server.db.model.User;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetAdmin implements ServerCommand {

    private static Set<UserSend> userList = new CopyOnWriteArraySet<>();

    static {
        userList.add(new UserSend("nanord", null));
    }

    @Override
    public String make(String txt) {
        StringBuilder str = new StringBuilder();
        if(txt != null) {
            UserSend userSend = new UserSend(txt, null);
            if(equalsAdmin(userSend)) {
                str.append("OK" + "\n");
            }
            else if(DataServer.ifUserName(userSend)) {
                userList.add(userSend);
                str.append("OK" + "\n");
            }
            else {
                str.append("User not found" + "\n");
            }
        }
        return str.toString();
    }

    public static boolean equalsAdmin(UserSend userSend) {
        for (UserSend x:
             userList) {
            if(x.getName().equalsIgnoreCase(userSend.getName())) {
                return true;
            }
        }
        return false;
    }
}
