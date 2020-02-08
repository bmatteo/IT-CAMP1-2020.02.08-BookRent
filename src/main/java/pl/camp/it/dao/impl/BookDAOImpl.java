package pl.camp.it.dao.impl;

import org.springframework.stereotype.Repository;
import pl.camp.it.dao.IBookDAO;
import pl.camp.it.model.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDAOImpl implements IBookDAO {

    private String fileName = "/home/mateusz/ITCamp-Kraków/2020.02.08-bookRent/src/main/resources/books.txt";

    @Override
    public void persistBook(Book book) {
        List<Book> temporaryBase = getAllBooks();

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))){

            for (Book tempBook : temporaryBase) {
                if(tempBook.getId() == book.getId()) {
                    temporaryBase.remove(tempBook);
                    break;
                }
            }

            temporaryBase.add(book);

            for (Book tempBook : temporaryBase) {
                bufferedWriter.write(tempBook.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book getBookByTitle(String title) {
        List<Book> temporaryBase = getAllBooks();

        for (Book book : temporaryBase) {
            if(book.getTitle().equals(title)) {
                return book;
            }
        }

        return null;
    }

    @Override
    public Book getBookByAuthor(String author) {
        List<Book> temporaryBase = getAllBooks();

        for (Book book : temporaryBase) {
            if(book.getAuthor().equals(author)) {
                return book;
            }
        }

        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> temporaryBase = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){

            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                temporaryBase.add(convertLineToBookObject(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return temporaryBase;
    }

    private Book convertLineToBookObject(String line) {
        String[] fields = line.split(";");

        Book result = new Book();

        result.setId(Integer.parseInt(fields[0]));
        result.setAuthor(fields[1]);
        result.setTitle(fields[2]);
        result.setIsbn(fields[3]);

        return result;
    }
}
