package mk.ukim.finki.lab.model;

import lombok.Data;
import mk.ukim.finki.lab.bootstrap.DataHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class Book {
    public String isbn;
    public String title;
    public String genre;
    public Integer year;
    public List<Author> authors;
    public BookStore bookStore;
    public Book(String isbn, String title, String genre, Integer year, ArrayList<Author> authors) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.authors = authors;
        Random random = new Random();
        int randomIndex = random.nextInt(DataHolder.bookStore.size());
        bookStore = DataHolder.bookStore.get(randomIndex);
        System.out.println(bookStore);
    }



}
