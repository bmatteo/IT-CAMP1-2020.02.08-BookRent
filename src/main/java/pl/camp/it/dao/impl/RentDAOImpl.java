package pl.camp.it.dao.impl;

import pl.camp.it.dao.IRentDAO;
import pl.camp.it.model.Rent;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentDAOImpl implements IRentDAO {

    private String fileName = "/home/mateusz/ITCamp-Kraków/2020.02.08-bookRent/src/main/resources/rents.txt";

    @Override
    public void persistRent(Rent rent) {
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
    public List<Rent> getRentsByBookId(int bookId) {
        return null;
    }

    @Override
    public List<Rent> getAllRents() {
        List<Rent> temporaryBase = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){

            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                temporaryBase.add(convertLineToRentObject(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return temporaryBase;
    }

    private Rent convertLineToRentObject(String line) {
        String[] fields = line.split(";");

        Rent result = new Rent();

        result.setId(Integer.parseInt(fields[0]));
        result.setStartDate(LocalDate.parse(fields[1]));
        result.setBookId(Integer.parseInt(fields[2]));
        result.setUserId(Integer.parseInt(fields[3]));
        result.setActive(Boolean.parseBoolean(fields[4]));

        return result;
    }
}
