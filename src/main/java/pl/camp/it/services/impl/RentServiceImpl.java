package pl.camp.it.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.dao.IBookDAO;
import pl.camp.it.dao.IRentDAO;
import pl.camp.it.model.Book;
import pl.camp.it.model.Rent;
import pl.camp.it.model.User;
import pl.camp.it.services.IRentService;
import pl.camp.it.session.SessionObject;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class RentServiceImpl implements IRentService {

    @Autowired
    IBookDAO bookDAO;

    @Autowired
    IRentDAO rentDAO;

    @Resource
    SessionObject sessionObject;

    @Override
    public boolean rentBookById(int id) {
        Book book = bookDAO.getBookById(id);
        List<Rent> allRentsForBook = rentDAO.getRentsByBookId(book.getId());

        for (Rent rent : allRentsForBook) {
            if(rent.isActive()) {
                return false;
            }
        }

        Rent rent = new Rent();

        rent.setId(new Random().nextInt());
        rent.setBookId(book.getId());
        rent.setActive(true);
        rent.setStartDate(LocalDate.now());
        rent.setUserId(sessionObject.getUser().getId());

        rentDAO.persistRent(rent);

        return true;
    }

    @Override
    public List<Rent> getAllRentsForUser(User user) {
        List<Rent> allRents = rentDAO.getAllRents();

        List<Rent> rentsForUser = new ArrayList<>();

        for (Rent rent : allRents) {
            if(rent.getUserId() == user.getId()) {
                rentsForUser.add(rent);
            }
        }

        return rentsForUser;
    }
}
