package org.alishev.course.model;


import javax.validation.constraints.*;

public class Book {
    private int id;
    @NotEmpty(message = "Book title should not be empty")
    @Size(min = 2, max = 100, message = "Book title too long or too short")
    private String title;
    @NotEmpty(message = "Author name should not be empty")
    @Size(min = 2, max = 100, message = "Author name too long or too short")
    private String author;
    @Min(value = 1500, message = "Release date should be older than 1500")
    private int release_date;

    public Book() {
    }

    public Book(int id, String title, String author, int release_date) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.release_date = release_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getRelease_date() {
        return release_date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setRelease_date(int release_date) {
        this.release_date = release_date;
    }
}
