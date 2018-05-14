package server.db.dao;

import server.db.model.Group;
import server.db.model.Message;
import server.db.model.User;

import java.sql.SQLException;
import java.util.List;

public interface GroupDao {
    Group get(String name);
    List<Group> getAll();
}
