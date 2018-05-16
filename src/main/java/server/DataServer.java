package server;

import commonData.InfoSend;
import commonData.MessageSend;
import commonData.UserSend;
import server.db.Factory;
import server.db.model.Group;
import server.db.model.User;
import server.db.service.UserService;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataServer {
    private static Set<User> userList = new ConcurrentSkipListSet<User>();
    private static Map<String, Group> groupMap = new ConcurrentHashMap<String, Group>();

    DataServer() {
        Stream<User> userStream = Factory.getUserService().getAll();
        if(userStream != null) {
            userList = userStream
                    .collect(Collectors.toSet());
        }
        Stream<Group> groupStream = Factory.getGroupService().getAll();
        if(groupStream != null) {
            groupMap = groupStream.collect(Collectors.toMap(
                    Group::getName,
                    Function.identity()
            ));
        }
    }


    public static UserSend addNewUser(UserSend userSend, InfoSend infoSend) {
        User newUser = new User(userSend);
        User oldUser = ifUser(userSend);
        if(oldUser != null) {
            userSend.setId(oldUser.getId());
            groupMap.get("general").addUser(oldUser, infoSend);
            return userSend;
        } else {
            Factory.getUserService().add(newUser);
            //Возможна проблема!
            userList.add(newUser);
            groupMap.get("general").addUser(newUser, infoSend);

            userSend.setId(newUser.getId());
            return userSend;
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
                /*group.sendMssage(new MessageSend(
                        null,
                        null,
                        "ResponseServer: Поприветствуйте: " + user.getName(),
                        group.getName()));
                        */
                return true;
            }
        }
    }

    public static void removeUser(String nameGroup, UserSend userSend, InfoSend infoSend) throws IOException{
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
        User user = ifUser(userSend);
        Group group = groupMap.get(nameGroup);
        //Если такая группа существует
        if(group != null) {
            //Если юзер пренадлежит группе
            if (group.getUserList().contains(user)) {
                groupMap.remove(nameGroup);
                Factory.getGroupService().delete(group);
                return true;
            }
        }
        return false;
    }

    private static User ifUser(UserSend userSend) {
        for (User x : userList) {
            if (x.getName().equalsIgnoreCase(userSend.getName())
                    && x.getPassword().equalsIgnoreCase(userSend.getPassword())) {
                return x;
            }
        }
        return null;
    }


}
