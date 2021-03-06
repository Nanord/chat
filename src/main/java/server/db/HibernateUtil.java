package server.db; /**
 * Created by Admin on 01.03.2018.
 */

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import server.db.model.Group;
import server.db.model.Message;
import server.db.model.User;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new Configuration()
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Message.class)
                    .addAnnotatedClass(Group.class)
                    .configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}