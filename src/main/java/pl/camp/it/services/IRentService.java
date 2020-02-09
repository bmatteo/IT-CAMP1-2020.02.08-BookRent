package pl.camp.it.services;

import pl.camp.it.model.Rent;
import pl.camp.it.model.User;

import java.util.List;

public interface IRentService {
    boolean rentBookById(int id);
    List<Rent> getAllRentsForUser(User user);
}
