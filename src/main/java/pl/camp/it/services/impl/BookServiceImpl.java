package pl.camp.it.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.dao.IBookDAO;
import pl.camp.it.model.Book;
import pl.camp.it.services.IBookService;

import java.util.List;

@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    IBookDAO bookDAO;

    @Override
    public void persistBook(Book book) {
        bookDAO.persistBook(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    @Override
    public Book getBookById(int id) {
        return bookDAO.getBookById(id);
    }

    public void setBookDAO(IBookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
}
