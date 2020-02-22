package pl.camp.it.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.dao.IBookDAO;
import pl.camp.it.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//@Repository
public class DBBookDAOImpl implements IBookDAO {

    @Autowired
    Connection connection;

    @Override
    public void persistBook(Book book) {
        try {
            String sql;
            PreparedStatement ps;
            if(book.getId() == 0) {
                sql = "INSERT INTO tbook (author, title, isbn) VALUES (?, ?, ?);";
                ps = this.connection.prepareStatement(sql);
            } else {
                sql = "UPDATE tbook SET author = ?, title = ?, isbn = ? WHERE id = ?;";
                ps = this.connection.prepareStatement(sql);
                ps.setInt(4, book.getId());
            }

            ps.setString(1, book.getAuthor());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getIsbn());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Błąd dodawania książki !!");
            e.printStackTrace();
        }
    }

    @Override
    public Book getBookByTitle(String title) {
        try {
            String sql = "SELECT * FROM tbook WHERE title = ?;";

            PreparedStatement ps = this.connection.prepareStatement(sql);

            ps.setString(1, title);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Book book = mapResultSetToBook(rs);

                return book;
            }
        } catch (SQLException e) {
            System.out.println("Błąd pobierania ksążki po tytule !");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Book getBookByAuthor(String author) {
        try {
            String sql = "SELECT * FROM tbook WHERE author = ?;";

            PreparedStatement ps = this.connection.prepareStatement(sql);

            ps.setString(1, author);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Book book = mapResultSetToBook(rs);

                return book;
            }
        } catch (SQLException e) {
            System.out.println("Błąd pobierania ksążki po autorze !");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Book getBookById(int id) {
        try {
            String sql = "SELECT * FROM tbook WHERE id = ?;";

            PreparedStatement ps = this.connection.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Book book = mapResultSetToBook(rs);

                return book;
            }
        } catch (SQLException e) {
            System.out.println("Błąd pobierania ksążki po autorze !");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tbook;";

            PreparedStatement ps = this.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Book book = mapResultSetToBook(rs);

                result.add(book);
            }
        } catch (SQLException e) {
            System.out.println("Błąd pobierania wszystkich książek !");
            e.printStackTrace();
        }

        return result;
    }

    private Book mapResultSetToBook(ResultSet rs) throws SQLException {
        Book book = new Book();

        book.setId(rs.getInt("id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setIsbn(rs.getString("isbn"));

        return book;
    }
}
