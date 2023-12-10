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
    public Long id;
    public String name;
    public String city;
    public String address;
    @OneToMany
    public List<Book> booksInStore;
    public BookStore(Long id,String name, String city, String address) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        booksInStore = new ArrayList<>();
    }

    public BookStore() {

    }
}
