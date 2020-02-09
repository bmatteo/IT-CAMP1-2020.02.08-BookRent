package pl.camp.it.services;

import pl.camp.it.model.Book;

import java.util.List;

public interface IBookService {
    void persistBook(Book book);
    List<Book> getAllBooks();
    Book getBookById(int id);
}
