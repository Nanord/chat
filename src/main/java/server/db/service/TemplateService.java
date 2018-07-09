package server.db.service;


import org.hibernate.LockMode;
import org.hibernate.Session;
import server.db.HibernateUtil;
import server.db.dao.TemplateDao;

import java.sql.SQLException;

/**
 * Created by Admin on 04.03.2018.
 */

public abstract class TemplateService<T> implements TemplateDao<T> {

    protected Session session;

    public void add(T entity) {
        session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(entity);
            session.lock(entity, LockMode.PESSIMISTIC_WRITE);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if(session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void update(T entity) {
        session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(entity);
            session.lock(entity, LockMode.PESSIMISTIC_WRITE);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if(session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void delete(T entity) {
        session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(entity);
            session.lock(entity, LockMode.PESSIMISTIC_WRITE);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
