package mk.ukim.finki.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.lab.bootstrap.DataHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String isbn;
    public String title;
    public String genre;
    public Integer year;
    @ManyToMany
    public List<Author> authors;
    @ManyToOne
    public BookStore bookStore;
    @OneToMany(cascade = CascadeType.REMOVE)
    public List<Review> reviews;
    public Book(String isbn, String title, String genre, Integer year, List<Author>authors) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.authors = authors;

    }
    public Book() {

    }
}
