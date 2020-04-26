package pl.camp.it.services.impl;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.camp.it.config.AppConfigurationTest;
import pl.camp.it.dao.IBookDAO;
import pl.camp.it.dao.IRentDAO;
import pl.camp.it.dao.IUserDAO;
import pl.camp.it.model.Book;
import pl.camp.it.model.Rent;
import pl.camp.it.model.User;
import pl.camp.it.model.UserRole;
import pl.camp.it.services.IRentService;
import pl.camp.it.session.SessionObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfigurationTest.class})
public class RentServiceImplTest {

    @Autowired
    IRentService rentService;

    @MockBean
    IRentDAO rentDAO;

    @Autowired
    IUserDAO userDAO;

    @Autowired
    IBookDAO bookDAO;

    @MockBean
    SessionObject sessionObject;

    @Test
    public void getAllRentsForUserTest() {
        Mockito.when(this.rentDAO.getAllRents()).thenReturn(rentDAOGetAllRentsBehavior());
        User mojZmockowanyUser = Mockito.mock(User.class);
        User user = new User();
        user.setId(1);
        user.setRole(UserRole.USER);
        user.setLogin("mateusz");
        user.setPass("617f41f48d1d4f9c787aed6b0ebc6f7d");
        int expectedRentCount = 4;
        int expectedRentId = 3;
        int expectedBookId = 3;
        LocalDate expectedStartDate = LocalDate.parse("2020-04-25");
        LocalDate expectedEndDate = LocalDate.parse("2020-05-09");

        List<Rent> rents =  this.rentService.getAllRentsForUser(user);

        Assert.assertEquals(expectedRentCount, rents.size());
        Rent rentToTest = rents.get(2);
        Assert.assertEquals(expectedRentId, rentToTest.getId());
        Assert.assertTrue(rentToTest.isActive());
        Assert.assertEquals(expectedBookId, rentToTest.getBookId());
        Assert.assertEquals(expectedStartDate, rentToTest.getStartDate());
        Assert.assertEquals(expectedEndDate, rentToTest.getEndDate());
        Assert.assertEquals(user.getId(), rentToTest.getUserId());
    }

    @Test
    public void getAllRentsForUserWhenNoRentsTest() {
        Mockito.when(this.rentDAO.getAllRents()).thenReturn(new ArrayList<>());
        User user = new User();
        user.setId(1);
        user.setRole(UserRole.USER);
        user.setLogin("mateusz");
        user.setPass("617f41f48d1d4f9c787aed6b0ebc6f7d");
        int expectedRentsCount = 0;

        List<Rent> rents =  this.rentService.getAllRentsForUser(user);

        Assert.assertEquals(expectedRentsCount, rents.size());
        verify(this.rentDAO, times(1)).getAllRents();
    }

    @Test
    public void getAllRentsForNullUserTest() {
        List<Rent> allBDRents = rentDAOGetAllRentsBehavior();
        Mockito.when(this.rentDAO.getAllRents()).thenReturn(allBDRents);
        User user = null;

        List<Rent> rents = this.rentService.getAllRentsForUser(user);

        Assert.assertEquals(allBDRents.size(), rents.size());
        for(int i = 0; i < rents.size(); i++) {
            if(rents.get(i).getId() != allBDRents.get(i).getId()) {
                Assert.fail();
            }
        }
    }

    @Test(expected = User.UserValidationException.class)
    public void getAllRentsForUserWithNullIdTest() {
        User user = new User();
        user.setPass("asjhgdf");
        user.setLogin("jghasdf");
        user.setRole(UserRole.USER);

        this.rentService.getAllRentsForUser(user);
    }

    public void getAllRentsForUserWithNullLoginAndRoleTest() {
        Mockito.when(this.rentDAO.getAllRents()).thenReturn(rentDAOGetAllRentsBehavior());
        User user = new User();
        user.setId(1);
        user.setPass("asjhgdf");
        int expectedResultListSize = 4;

        List<Rent> rents = this.rentService.getAllRentsForUser(user);

        Assert.assertEquals(expectedResultListSize, rents.size());
    }

    @Test
    public void rentBookByIdTest() {
        Mockito.when(this.bookDAO.getBookById(5)).thenReturn(generateTestBook());
        Mockito.when(this.rentDAO.getRentsByBookId(5)).thenReturn(rentDAOGetAllNotActiveRentsBehavior());
        Mockito.when(this.sessionObject.getUser()).thenReturn(generateTestUser());
        int bookToRent = 5;

        boolean result = this.rentService.rentBookById(bookToRent);

        Assert.assertTrue(result);
    }

    @Test
    public void rentBookWhichNotExistTest() {
        Mockito.when(this.bookDAO.getBookById(5)).thenReturn(null);
        Mockito.when(this.rentDAO.getRentsByBookId(5)).thenReturn(rentDAOGetAllNotActiveRentsBehavior());
        Mockito.when(this.sessionObject.getUser()).thenReturn(generateTestUser());
        int bookToRent = 5;

        boolean result = this.rentService.rentBookById(bookToRent);

        Assert.assertFalse(result);
    }

    @Test
    public void rentBookWhenItIsAlreadyRentTest() {
        Mockito.when(this.bookDAO.getBookById(5)).thenReturn(generateTestBook());
        Mockito.when(this.rentDAO.getRentsByBookId(5)).thenReturn(rentDAOGetAllRentsBehavior());
        Mockito.when(this.sessionObject.getUser()).thenReturn(generateTestUser());
        int bookToRent = 5;

        boolean result = this.rentService.rentBookById(bookToRent);

        Assert.assertFalse(result);
    }

    private List<Rent> rentDAOGetAllRentsBehavior() {
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

    private Book generateTestBook() {
        Book book = new Book();
        book.setId(5);
        book.setAuthor("iusdfhogudhs");
        book.setTitle("uysgdff");
        book.setIsbn("kjsaydgifyasdyfuig");

        return book;
    }

    private List<Rent> rentDAOGetAllNotActiveRentsBehavior() {
        Rent rent1 = new Rent();
        rent1.setId(1);
        rent1.setActive(false);
        rent1.setBookId(1);
        rent1.setStartDate(LocalDate.parse("2020-04-25"));
        rent1.setUserId(1);

        Rent rent2 = new Rent();
        rent2.setId(2);
        rent2.setActive(false);
        rent2.setBookId(2);
        rent2.setStartDate(LocalDate.parse("2020-04-25"));
        rent2.setUserId(1);

        Rent rent3 = new Rent();
        rent3.setId(3);
        rent3.setActive(false);
        rent3.setBookId(3);
        rent3.setStartDate(LocalDate.parse("2020-04-25"));
        rent3.setUserId(1);

        Rent rent4 = new Rent();
        rent4.setId(4);
        rent4.setActive(false);
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

    private User generateTestUser() {
        User user = new User();
        user.setId(1);
        user.setPass("jhsagkfjhgsdf");
        user.setLogin("jhasgkjhasg");
        user.setRole(UserRole.USER);

        return user;
    }
}
