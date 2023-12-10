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
    @Column
    public String isbn;
    public String title;
    public String genre;
    public Integer year;
    @OneToMany
    public List<Author> authors;

    @ManyToOne
    public BookStore bookStore;
    @OneToMany
    public List<Review> reviews;
    public Book(String isbn, String title, String genre, Integer year, List<Author>authors) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.authors = authors;
        Random random = new Random();
        int randomIndex = random.nextInt(DataHolder.bookStore.size());
        bookStore = DataHolder.bookStore.get(randomIndex);
        reviews = new ArrayList<>();
    }
    public Book() {

    }
}
