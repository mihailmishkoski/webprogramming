package mk.ukim.finki.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookStore {

    private String id;
    private String name;
    private String city;
    private String address;
    private List<Book> booksInStore;
    public BookStore(String id, String name, String city, String address) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        booksInStore = new ArrayList<>();
    }

}
