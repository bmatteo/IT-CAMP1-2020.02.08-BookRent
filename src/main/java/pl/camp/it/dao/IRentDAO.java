package pl.camp.it.dao;

import pl.camp.it.model.Rent;

import java.util.List;

public interface IRentDAO {
    void persistRent(Rent rent);
    List<Rent> getRentsByBookId(int bookId);
    List<Rent> getAllRents();
}
