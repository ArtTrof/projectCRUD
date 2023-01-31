package org.example.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;
    @NotEmpty(message = "Book name must be input")
    @Size(min=2,max=100,message = "Name must have between 2 and 100 char")
    private String bookName;
    @NotEmpty(message = "Author  must be input")
    @Size(min=2,max=100,message = "Author must have between 2 and 100 char")
    private String author;
    @Min(value=1000,message = "Year must be above 1000")
    private int year;

    public Book() {
    }

    public Book(String bookName, String author, int year) {
        this.bookName = bookName;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
