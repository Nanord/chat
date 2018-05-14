package server.db.service;

import org.hibernate.query.Query;
import server.db.HibernateUtil;
import server.db.dao.TemplateDao;
import server.db.dao.UserDao;
import server.db.model.User;

import java.sql.SQLException;
import java.util.List;

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
            List<User> users = query1.getResultList();
            if(users.size() != 1) {
                return null;
            }
            else {
                user = users.get(0);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if(session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }

    public List<User> getAll() {
        session = null;
        List<User> users = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String hql = "from User";

            Query<User> query =  session.createQuery(hql);
            users = query.getResultList();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if(session != null && session.isOpen()) {
                session.close();
            }
            return users;
        }
    }

}
