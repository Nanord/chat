package server.db.service;

import commonData.UserSend;
import org.hibernate.query.Query;
import server.db.HibernateUtil;
import server.db.dao.TemplateDao;
import server.db.dao.UserDao;
import server.db.model.Group;
import server.db.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

public class UserService extends  TemplateService<User> implements UserDao {

    public User getByEnter(User user) {
        session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            /*Query query =  session.createNativeQuery("select * from User where User.name = :name and User.password = :password");
            query.setParameter(name, name);
            query.setParameter(password, password);
*/
            String hql = "from User u where u.name = :name and u.password = :password";

            Query query1 = session.createQuery(hql);
            query1.setParameter("name", user.getName());
            query1.setParameter("password", user.getPassword());
            List users = query1.getResultList();
            if(users.size() != 1) {
                return null;
            }
            else {
                user = (User)users.get(0);
            }
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
        return user;
    }

    public Stream<User> getAll() {
        session = null;
        List<User> users = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String hql = "from User";

            Query<User> query =  session.createQuery(hql, User.class);
            users = query.getResultList();

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
            if(users != null)
                return users.stream();
            return null;
        }
    }

    @Override
    public boolean eqUser(UserSend userSend) {
        return false;
    }

    @Override
    public void exitGroup(Group group) {
    }
}
