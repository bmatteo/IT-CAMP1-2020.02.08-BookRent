package pl.camp.it.dao.impl;

import pl.camp.it.dao.IRentDAO;
import pl.camp.it.model.Rent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HibernateRentDAOImplStub implements IRentDAO {

    @Override
    public void persistRent(Rent rent) {
        System.out.println("Zapisało się !!");
    }

    @Override
    public List<Rent> getRentsByBookId(int bookId) {
        return new ArrayList<>();
    }

    @Override
    public List<Rent> getAllRents() {
        Rent rent1 = new Rent();
        rent1.setId(1);
        rent1.setActive(true);
        rent1.setBookId(1);
        rent1.setStartDate(LocalDate.parse("2020-04-25"));
        rent1.setUserId(1);

        Rent rent2 = new Rent();
        rent2.setId(2);
        rent2.setActive(true);
        rent2.setBookId(2);
        rent2.setStartDate(LocalDate.parse("2020-04-25"));
        rent2.setUserId(1);

        Rent rent3 = new Rent();
        rent3.setId(3);
        rent3.setActive(true);
        rent3.setBookId(3);
        rent3.setStartDate(LocalDate.parse("2020-04-25"));
        rent3.setUserId(1);

        Rent rent4 = new Rent();
        rent4.setId(4);
        rent4.setActive(true);
        rent4.setBookId(4);
        rent4.setStartDate(LocalDate.parse("2020-04-25"));
        rent4.setUserId(1);

        ArrayList<Rent> result = new ArrayList<>();
        result.add(rent1);
        result.add(rent2);
        result.add(rent3);
        result.add(rent4);

        return result;
    }
}
