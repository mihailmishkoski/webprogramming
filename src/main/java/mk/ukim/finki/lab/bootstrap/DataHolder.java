package mk.ukim.finki.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.lab.model.Author;
import mk.ukim.finki.lab.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
@Component
public class DataHolder {
    public static List<Author> authors = null;

    public static List<Book> books = null;

    @PostConstruct
    public void init()
    {
        authors = new ArrayList<>();
        authors.add(new Author(123L,"Blazhe","Koneski","Kratka biografija za avtorot"));
        authors.add(new Author(124L,"Fyodor","Dostoevsky","Kratka biografija za avtorot"));
        authors.add(new Author(125L,"William","Shakespeare","Kratka biografija za avtorot"));
        authors.add(new Author(126L,"Leo","Tolstoy","Kratka biografija za avtorot"));

        books = new ArrayList<>();
        books.add(new Book("123-123-123", "Zoki-Poki", "Comedy", 2000, new ArrayList<>()));
        books.add(new Book("123-123-124", "Volshebnoto Samarche", "Drama", 1998, new ArrayList<>()));
        books.add(new Book("123-123-125", "Devojkite na Marko", "Romance", 1999, new ArrayList<>()));
    }

}
