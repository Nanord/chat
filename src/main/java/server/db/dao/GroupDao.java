package server.db.dao;

import server.db.model.Group;
import server.db.model.Message;
import server.db.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

public interface GroupDao extends TemplateDao<Group>{
    Group get(String name);
    Stream<Group> getAll();
}
