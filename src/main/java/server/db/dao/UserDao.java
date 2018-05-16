package server.db.dao;

import commonData.UserSend;
import server.db.model.Group;
import server.db.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

public interface UserDao extends  TemplateDao<User> {
    User getByEnter(User user);
    Stream<User> getAll();
    boolean eqUser(UserSend userSend);
}
