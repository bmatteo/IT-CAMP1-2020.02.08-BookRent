package pl.camp.it.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.dao.IBookDAO;
import pl.camp.it.model.Book;

import java.util.List;

@Repository
public class HibernateBookDAOImpl implements IBookDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void persistBook(Book book) {
        Transaction tx = null;
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(book);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        }
    }

    @Override
    public Book getBookByTitle(String title) {
        Session session = sessionFactory.openSession();
        try {
            return (Book) session
                    .createQuery("FROM pl.camp.it.model.Book WHERE title = '" + title + "'")
                    .uniqueResult();
        } finally {
            session.close();
        }

    }

    @Override
    public Book getBookByAuthor(String author) {
        Session session = sessionFactory.openSession();
        Book book = (Book) session
                .createQuery("FROM pl.camp.it.model.Book WHERE author = '" + author + "'")
                .uniqueResult();
        session.close();
        return book;
    }

    @Override
    public Book getBookById(int id) {
        Session session = sessionFactory.openSession();
        return (Book) session
                .createQuery("FROM pl.camp.it.model.Book WHERE id = " + id)
                .uniqueResult();
    }

    @Override
    public List<Book> getAllBooks() {
        Session session = sessionFactory.openSession();
        return session
                .createQuery("FROM pl.camp.it.model.Book")
                .list();
    }
}
