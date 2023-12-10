package mk.ukim.finki.lab.repository;

import mk.ukim.finki.lab.bootstrap.DataHolder;
import mk.ukim.finki.lab.model.Book;
import mk.ukim.finki.lab.model.BookStore;
import mk.ukim.finki.lab.repository.jpa.BookStoreInterface;
import mk.ukim.finki.lab.service.BookStoreService;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookStoreRepository {


    private final BookStoreInterface bsi;

    public BookStoreRepository(BookStoreInterface bsi) {
        this.bsi = bsi;
    }

    public List<BookStore> findAll() {
        return DataHolder.bookStore;
    }


}
