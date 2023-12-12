package mk.ukim.finki.lab.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "book_store")
public class BookStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
    public String city;
    public String address;
    @OneToMany
    public List<Book> booksInStore;
    public BookStore(String name, String city, String address) {
        this.name = name;
        this.city = city;
        this.address = address;
        booksInStore = new ArrayList<>();
    }

    public BookStore() {

    }
}
