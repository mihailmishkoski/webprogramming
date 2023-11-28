package mk.ukim.finki.lab.service.impl;

import mk.ukim.finki.lab.model.BookStore;
import mk.ukim.finki.lab.repository.BookStoreRepository;
import mk.ukim.finki.lab.service.BookService;
import mk.ukim.finki.lab.service.BookStoreService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookStoreImpl implements BookStoreService {
    private final BookStoreRepository bsr;

    public BookStoreImpl(BookStoreRepository bsr) {
        this.bsr = bsr;
    }

    @Override
    public List<BookStore> findAll() {
        return bsr.findAll();
    }

}
