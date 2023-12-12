package mk.ukim.finki.lab.service;

import mk.ukim.finki.lab.model.Book;
import mk.ukim.finki.lab.model.BookStore;
import mk.ukim.finki.lab.service.jpa.BookStoreJpa;

import java.util.List;

public interface BookStoreService {
    List<BookStore> findAll();

    BookStore findById(Long bookStoreId);
    void saveBookStore(BookStore bookStoreId);
}
