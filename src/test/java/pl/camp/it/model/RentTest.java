package pl.camp.it.model;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class RentTest {

    @Test
    public void rentObjectTest() {
        Rent rentObject = new Rent();
        int id = 1;
        LocalDate startDate = LocalDate.parse("2020-01-10");
        int bookId = 300;
        int userId = 23;
        boolean active = true;
        int expectedId = 1;
        LocalDate expectedStartDate = LocalDate.parse("2020-01-10");
        LocalDate expectedEndDate = LocalDate.parse("2020-01-24");
        int expectedBookId = 300;
        int expectedUserId = 23;

        rentObject.setId(id);
        rentObject.setStartDate(startDate);
        rentObject.setBookId(bookId);
        rentObject.setUserId(userId);
        rentObject.setActive(active);

        Assert.assertEquals(expectedId, rentObject.getId());
        Assert.assertEquals(expectedStartDate, rentObject.getStartDate());
        Assert.assertEquals(expectedEndDate, rentObject.getEndDate());
        Assert.assertEquals(expectedBookId, rentObject.getBookId());
        Assert.assertEquals(expectedUserId, rentObject.getUserId());
        Assert.assertTrue(rentObject.isActive());
    }
}
