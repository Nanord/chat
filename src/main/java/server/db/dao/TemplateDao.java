package server.db.dao;

import java.sql.SQLException;

public interface TemplateDao<T> {
    void add(T entity);
    void update(T entity);
    void delete(T entity);
}
