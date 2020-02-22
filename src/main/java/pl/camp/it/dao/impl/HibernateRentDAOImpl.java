package pl.camp.it.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.dao.IRentDAO;
import pl.camp.it.model.Rent;

import java.util.List;

@Repository
public class HibernateRentDAOImpl implements IRentDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void persistRent(Rent rent) {
        Transaction tx = null;
        try {
            Session session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(rent);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        }
    }

    @Override
    public List<Rent> getRentsByBookId(int bookId) {
        Session session = sessionFactory.openSession();
        return session
                .createQuery("FROM pl.camp.it.model.Rent WHERE bookId = " + bookId)
                .list();
    }

    @Override
    public List<Rent> getAllRents() {
        Session session = sessionFactory.openSession();
        return session
                .createQuery("FROM pl.camp.it.model.Rent")
                .list();
    }
}
