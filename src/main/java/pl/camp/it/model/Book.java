package pl.camp.it.model;

public class Book {
    private int id;
    private String author;
    private String title;
    private String isbn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(this.id)
                .append(";")
                .append(this.author)
                .append(";")
                .append(this.title)
                .append(";")
                .append(this.isbn);

        return stringBuilder.toString();
    }
}
