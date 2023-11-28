package mk.ukim.finki.lab.service.impl;

import mk.ukim.finki.lab.bootstrap.DataHolder;
import mk.ukim.finki.lab.model.Author;
import mk.ukim.finki.lab.model.Book;
import mk.ukim.finki.lab.repository.AuthorRepository;
import mk.ukim.finki.lab.repository.BookRepository;
import mk.ukim.finki.lab.service.BookService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bs;

    private final AuthorRepository ar;



    public BookServiceImpl(BookRepository bs, AuthorRepository ar) {
        this.bs = bs;
        this.ar = ar;
    }

    @Override
    public List<Book> listBooks() {
        return bs.listBooks();
    }

    @Override
    public Author addAuthorToBook(Long authorId, String isbn) {
        Optional<Author> author = ar.findById(authorId);
        Book book = bs.findByIsbn(isbn);

        if (author.isPresent() && book != null) {
            return bs.addAuthorToBook(author.get(), book);
        } else {
            return null;
        }
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bs.findByIsbn(isbn);
    }

    @Override
    public void saveBook(String isbn, String title, String genre, Integer year, String bookStoreId) {
        bs.saveBook(isbn, title, genre, year, bookStoreId);
    }

}
