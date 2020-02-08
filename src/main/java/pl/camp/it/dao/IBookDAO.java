package pl.camp.it.dao;

import pl.camp.it.model.Book;

import java.util.List;

public interface IBookDAO {
    void persistBook(Book book);
    Book getBookByTitle(String title);
    Book getBookByAuthor(String author);
    List<Book> getAllBooks();
}
