package server;

import commonData.DATA;
import commonData.InfoSend;
import commonData.MessageSend;
import commonData.UserSend;
import server.db.Factory;
import server.db.model.Group;
import server.db.model.User;
import server.subscription.EventManager;
import server.subscription.EventType;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class DataServer {

    private static Set<User> userList = new CopyOnWriteArraySet<>();
    private static Map<String, Group> groupMap = new ConcurrentHashMap<>();


    static void  init() {
        Stream<User> userStream = Factory.getUserService().getAll();
        if(userStream != null) {
            userList = userStream
                    .collect(Collectors.toCollection(CopyOnWriteArraySet::new));
        }
        Stream<Group> groupStream = Factory.getGroupService().getAll();
        if(groupStream != null) {
            groupMap = groupStream.collect(Collectors.toConcurrentMap(
                    Group::getName,
                    Function.identity()
            ));
        }
        //Добавляем main группу(Stream не будет = null:( )
        if(groupMap.size() == 0){
            Group group = new Group(DATA.getMainGroup());
            Factory.getGroupService().add(group);
            groupMap.put(group.getName(), group);
        }
        //Добавляем Admin
        if(userList.size() == 0) {
            User admin = new User("Admin", "123");
            Factory.getUserService().add(admin);
            userList.add(admin);
        }
    }


    public static UserSend addNewUser(UserSend userSend, InfoSend infoSend) {
        if(userSend.getName() == null || userSend.getPassword() == null) {
            return null;
        }
        User newUser = new User(userSend);
        User oldUser = ifUser(userSend);
        if(oldUser != null) {
            userSend.setId(oldUser.getId());
            groupMap.get("general").addUser(oldUser, infoSend);
            return userSend;
        } else {
            if(ifUserName(userSend) == null) {
                Factory.getUserService().add(newUser);
                //Возможна проблема!
                userList.add(newUser);
                groupMap.get(DATA.getMainGroup()).addUser(newUser, infoSend);

                userSend.setId(newUser.getId());
                return userSend;
            } else
                return null;

        }
    }

    public static Group getGroup(String namGroup) {
        return groupMap.get(namGroup);
    }

    public static void addOnlineUesr(String nameGroup, InfoSend infoSend) {
        Group group = getGroup(nameGroup);
        group.addOnlineUser(infoSend);
    }

    public static boolean addGroup(String nameGroup, UserSend userSend, InfoSend infoSend) {
        if(groupMap.containsKey(nameGroup))
            return false;
        else {
            //Ищем соответсвие для добавления юзера в созданную группу
            User user = ifUser(userSend);
            if(user != null) {
                //Создаём и сохраняем новую группу
                Group group = new Group(nameGroup, user, infoSend);
                Factory.getGroupService().add(group);
                groupMap.put(group.getName(), group);
                return true;
            }
            else {
                return false;
            }
        }
    }

    public static void exitOnlineUser(String nameGroup, InfoSend infoSend) {
        groupMap.get(nameGroup).removeOnlineUser(infoSend);
    }

    public static boolean addUserInGroup(String nameGroup, UserSend userSend, InfoSend infoSend) throws IOException {
        if(!groupMap.containsKey(nameGroup)) {
            return false;
        }
        else {
            User user = ifUser(userSend);
            if (user == null) {
                return false;
            } else {
                Group group = groupMap.get(nameGroup);
                group.addUser(user, infoSend);
                group.sendMssage(new MessageSend(
                        null,
                        null,
                        "ResponseServer: Поприветствуйте: " + user.getName(),
                        group.getName()));
                return true;
            }
        }
    }

    public static void removeUserinGroup(String nameGroup, UserSend userSend, InfoSend infoSend) throws IOException{
        Group group = groupMap.get(nameGroup);
        User user = ifUser(userSend);
        group.sendMssage(new MessageSend(
                null,
                null,
                "ResponseServer: Мы будем скучать по " + user.getName() + ":(",
                group.getName()));
        group.removeUser(user, infoSend);
    }

    public static boolean deleteGroup(String nameGroup, UserSend userSend) {
        if(nameGroup.equals(DATA.getMainGroup()))
            return false;
        User user = ifUser(userSend);
        Group group = groupMap.get(nameGroup);
        //Если такая группа существует
        if(group != null) {
            //Если юзер пренадлежит группе
            if (group.containUser(user) != null) {
                groupMap.remove(nameGroup);
                Factory.getGroupService().delete(group);
                return true;
            }
        }
        return false;
    }

    public static User ifUser(UserSend userSend) {
        for (User x : userList) {
            if (x.getName().equalsIgnoreCase(userSend.getName())
                    && x.getPassword().equalsIgnoreCase(userSend.getPassword())) {
                return x;
            }
        }
        return null;
    }

    public static User ifUserName(UserSend userSend) {
        for (User x : userList) {
            if (x.getName().equalsIgnoreCase(userSend.getName())) {
                return x;
            }
        }
        return null;
    }

    public static InfoSend ifOnlineUser(String name) {
        InfoSend infoSend = null;
        for (Group g:
                DataServer.getGroupMap().values()) {
            for (InfoSend is:
                    g.getOnlineUsers()) {
                if(is.getUserSend().getName().equalsIgnoreCase(name))
                    infoSend = is;
            }
        }
        return infoSend;
    }

    public static Set<User> getUserList() {
        return userList;
    }

    public static boolean removeUser(String userName) {
        InfoSend infoSend = ifOnlineUser(userName);
        User user = ifUserName(new UserSend(userName, null));
        if(user != null) {
            Factory.getUserService().delete(user);
            if(infoSend == null)
                DataServer.getGroupMap().values().forEach( x -> x.removeUser(user));
            else
                DataServer.getGroupMap().values().forEach(x -> x.removeUser(user, infoSend));
            return true;
        }
        return false;

    }



    public static Map<String, Group> getGroupMap() {
        return groupMap;
    }
}
