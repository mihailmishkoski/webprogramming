package mk.ukim.finki.lab.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Book {
    public String isbn;
    public String title;
    public String genre;
    public Integer year;
    public List<Author> authors;

    public Book(String isbn, String title, String genre, Integer year, ArrayList<Author> authors) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.authors = authors;
    }

    public List<Author> getAuthors() {
        return authors;
    }
}
