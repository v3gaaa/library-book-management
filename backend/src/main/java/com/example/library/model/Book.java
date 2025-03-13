package com.example.library.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date publishedDate;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private boolean availability = true;

    public Book() {}

    public Book(String title, String author, String genre, Date publishedDate, String isbn, boolean availability) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publishedDate = publishedDate;
        this.isbn = isbn;
        this.availability = availability;
    }

    //const para mock
    public Book(Integer id, String title, String author, String genre, Date publishedDate, String isbn, boolean availability) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publishedDate = publishedDate;
        this.isbn = isbn;
        this.availability = availability;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public Date getPublishedDate() { return publishedDate; }
    public void setPublishedDate(Date publishedDate) { this.publishedDate = publishedDate; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public boolean isAvailability() { return availability; }
    public void setAvailability(boolean availability) { this.availability = availability; }
}
