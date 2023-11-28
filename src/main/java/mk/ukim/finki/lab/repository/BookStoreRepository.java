package mk.ukim.finki.lab.repository;

import mk.ukim.finki.lab.bootstrap.DataHolder;
import mk.ukim.finki.lab.model.BookStore;
import mk.ukim.finki.lab.service.BookStoreService;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookStoreRepository {


    public List<BookStore> findAll() {
        return DataHolder.bookStore;
    }


}
