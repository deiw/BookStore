package pl.majorczyk.mybookstore.model;

import java.sql.Timestamp;

public class Book {
    private long id;
    private String title;
    private String author;
    private String genre;
    private String description;
    private String url;
    private double price;
    private Timestamp bdate;

    public Book(){}
    public Book(Book book){
        this.id=book.id;
        this.title =book.title;
        this.author=book.author;
        this.genre=book.genre;
        this.description=book.description;
        this.url=book.url;
        this.price=book.price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getBdate() {
        return bdate;
    }

    public void setBdate(Timestamp bdate) {
        this.bdate = bdate;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
