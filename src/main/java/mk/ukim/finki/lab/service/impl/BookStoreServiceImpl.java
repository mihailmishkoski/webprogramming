package mk.ukim.finki.lab.service.impl;

import mk.ukim.finki.lab.model.BookStore;
import mk.ukim.finki.lab.repository.BookStoreRepository;
import mk.ukim.finki.lab.service.BookStoreService;
import mk.ukim.finki.lab.service.jpa.BookStoreJpa;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookStoreServiceImpl implements BookStoreService {
    private final BookStoreRepository bsr;

    private final BookStoreJpa bsjpa;

    public BookStoreServiceImpl(BookStoreRepository bsr, BookStoreJpa bsjpa) {
        this.bsr = bsr;
        this.bsjpa = bsjpa;
    }

    @Override
    public List<BookStore> findAll() {
        return bsr.findAll();
    }

    @Override
    public BookStore findById(Long bookStoreId) {
        return bsr.findById(bookStoreId);
    }

    @Override
    public void saveBookStore(BookStore bookStore) {
        bsjpa.save(bookStore);
    }


}
