package mk.ukim.finki.lab.bootstrap;
import jakarta.annotation.PostConstruct;
import mk.ukim.finki.lab.model.Author;
import mk.ukim.finki.lab.model.Book;
import mk.ukim.finki.lab.model.BookStore;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
@Component
public class DataHolder {
    public static List<Author> authors = null;
    public static List<Book> books = null;
    public static List<BookStore> bookStore = null;
    @PostConstruct
    public void init()
    {
        bookStore = new ArrayList<>();
//        bookStore.add(new BookStore(1234L, "Misterija","Makedonski Brod","ul.Partizanska"));
//        bookStore.add(new BookStore(1235L, "Nova","Makedonski Brod","ul.Porechka"));
//        bookStore.add(new BookStore(1236L, "Stara","Makedonski Brod","ul.Goce Delchev"));
//        bookStore.add(new BookStore(1237L, "Shkolska","Makedonski Brod","ul.Mirche Acev"));
//        bookStore.add(new BookStore(1238L, "SreknoDete","Makedonski Brod","ul.Nikola Tesla"));


        authors = new ArrayList<>();
//        authors.add(new Author(1L,"Blazhe","Koneski","Kratka biografija za avtorot", LocalDate.of(1990, 5, 15)));
//        authors.add(new Author(2L,"Fyodor","Dostoevsky","Kratka biografija za avtorot",LocalDate.of(1991, 5, 15)));
//        authors.add(new Author(3L,"William","Shakespeare","Kratka biografija za avtorot",LocalDate.of(1970, 5, 15)));
//        authors.add(new Author(4L,"Leo","Tolstoy","Kratka biografija za avtorot",LocalDate.of(1980, 5, 15)));


        books = new ArrayList<>();
//        books.add(new Book("123-123-123", "Zoki-Poki", "Comedy", 2000, new ArrayList<>()));
//        books.add(new Book("123-123-124", "Volshebnoto-Samarche", "Drama", 1998, new ArrayList<>()));
//        books.add(new Book("123-123-125", "Devojkite-na-Marko", "Romance", 1999, new ArrayList<>()));
    }
}