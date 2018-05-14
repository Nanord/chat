package server.db.service;

import org.hibernate.query.Query;
import server.db.HibernateUtil;
import server.db.dao.GroupDao;
import server.db.model.Group;
import server.db.model.Message;
import server.db.model.User;

import java.util.List;

public class GroupService extends TemplateService<Group> implements GroupDao {
    @Override
    public Group get(String name) {
        session = null;
        Group group = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            /*Query query =  session.createNativeQuery("select * from User where User.name = :name and User.password = :password");
            query.setParameter(name, name);
            query.setParameter(password, password);
*/
            String hql = "from Group g where g.name = :name";

            Query query1 = session.createQuery(hql);
            query1.setParameter("name", name);
            List<Group> groups = query1.getResultList();
            if(groups.size() != 1) {
                return null;
            }
            else {
                group = groups.get(0);
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
        return group;
    }

    @Override
    public List<Group> getAll() {
        session = null;
        List<Group> groups = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String hql = "from Group";

            Query<Group> query =  session.createQuery(hql);
            groups = query.getResultList();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if(session != null && session.isOpen()) {
                session.close();
            }
            return groups;
        }
    }

}
