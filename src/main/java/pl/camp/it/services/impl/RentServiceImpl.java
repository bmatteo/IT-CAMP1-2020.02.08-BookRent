package pl.camp.it.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.dao.IBookDAO;
import pl.camp.it.dao.IRentDAO;
import pl.camp.it.model.Book;
import pl.camp.it.model.Rent;
import pl.camp.it.services.IRentService;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class RentServiceImpl implements IRentService {

    @Autowired
    IBookDAO bookDAO;

    @Autowired
    IRentDAO rentDAO;

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
        //TODO rozpoznawanie userów przez sesje
        rent.setUserId(1);

        rentDAO.persistRent(rent);

        return true;
    }
}
