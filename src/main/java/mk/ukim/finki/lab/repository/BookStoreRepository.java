package mk.ukim.finki.lab.repository;

import mk.ukim.finki.lab.model.BookStore;
import mk.ukim.finki.lab.service.jpa.BookStoreJpa;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookStoreRepository {


    private final BookStoreJpa bsi;

    public BookStoreRepository(BookStoreJpa bsi) {
        this.bsi = bsi;
    }

    public List<BookStore> findAll() {
        return bsi.findAll();
    }
    public BookStore findById(Long bookStoreId)
    {
        return bsi.findById(bookStoreId).orElse(null);
    }

}
