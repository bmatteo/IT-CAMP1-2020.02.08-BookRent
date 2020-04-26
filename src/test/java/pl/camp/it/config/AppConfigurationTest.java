package pl.camp.it.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.camp.it.dao.IBookDAO;
import pl.camp.it.dao.IRentDAO;
import pl.camp.it.dao.IUserDAO;
import pl.camp.it.dao.impl.HibernateRentDAOImplStub;
import pl.camp.it.model.Book;
import pl.camp.it.model.User;

import java.util.List;

@Configuration
@ComponentScan({"pl.camp.it.contollers", "pl.camp.it.services.impl", "pl.camp.it.session"})
public class AppConfigurationTest {
    /*@Bean
    public IBookDAO bookDAO() {
        return new IBookDAO() {
            @Override
            public void persistBook(Book book) {

            }

            @Override
            public Book getBookByTitle(String title) {
                return null;
            }

            @Override
            public Book getBookByAuthor(String author) {
                return null;
            }

            @Override
            public Book getBookById(int id) {
                return null;
            }

            @Override
            public List<Book> getAllBooks() {
                return null;
            }
        };
    }

    @Bean
    public IUserDAO userDAO() {
        return new IUserDAO() {
            @Override
            public void persistUser(User user) {

            }

            @Override
            public User getUserByLogin(String login) {
                return null;
            }
        };
    }

    @Bean
    public IRentDAO rentDAO() {
        return new HibernateRentDAOImplStub();
    }*/

    @Bean
    public IBookDAO bookDAO() {
        return Mockito.mock(IBookDAO.class);
    }

    @Bean
    public IUserDAO userDAO() {
        return Mockito.mock(IUserDAO.class);
    }
}
