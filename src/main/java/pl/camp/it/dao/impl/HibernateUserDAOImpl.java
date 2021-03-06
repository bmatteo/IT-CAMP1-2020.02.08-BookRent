package pl.camp.it.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.dao.IUserDAO;
import pl.camp.it.model.User;

@Repository
public class HibernateUserDAOImpl implements IUserDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void persistUser(User user) {
        Transaction tx = null;
        try {
            Session session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(user);
            tx.commit();
        } catch (Exception e) {
            if(tx !=  null) {
                tx.rollback();
            }
        }
    }

    @Override
    public User getUserByLogin(String login) {
        Session session = sessionFactory.openSession();
        return (User) session
                .createQuery("FROM pl.camp.it.model.User WHERE login = '" + login + "'")
                .uniqueResult();
    }
}
