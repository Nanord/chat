package server.db.dao;

import server.db.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends  TemplateDao<User> {
    User getByEnter(User user);
    List<User> getAll();
}
