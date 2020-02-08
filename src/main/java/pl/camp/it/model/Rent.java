package pl.camp.it.model;

import java.time.LocalDate;

public class Rent {
    private int id;
    private LocalDate startDate;
    private int bookId;
    private int userId;
    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.startDate.plusWeeks(2);
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(this.id)
                .append(";")
                .append(this.startDate)
                .append(";")
                .append(this.bookId)
                .append(";")
                .append(this.userId)
                .append(";")
                .append(this.active);

        return stringBuilder.toString();
    }
}
