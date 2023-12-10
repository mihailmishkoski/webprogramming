package mk.ukim.finki.lab.service.impl;

import mk.ukim.finki.lab.bootstrap.DataHolder;
import mk.ukim.finki.lab.model.Author;
import mk.ukim.finki.lab.model.Book;
import mk.ukim.finki.lab.model.BookStore;
import mk.ukim.finki.lab.repository.AuthorRepository;
import mk.ukim.finki.lab.repository.BookRepository;
import mk.ukim.finki.lab.repository.BookStoreRepository;
import mk.ukim.finki.lab.service.BookService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository br;

    private final AuthorRepository ar;

    private final BookStoreImpl bsi;

    public BookServiceImpl(BookRepository br, AuthorRepository ar, BookStoreImpl bsi) {
        this.br = br;
        this.ar = ar;
        this.bsi = bsi;
    }

    @Override
    public List<Book> listBooks() {
        return br.listBooks();
    }

    @Override
    public Author addAuthorToBook(Author author, Book book) {
            return br.addAuthorToBook(author, book);
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return br.findByIsbn(isbn);
    }

    @Override
    public void saveBook(String isbn, String title, String genre, Integer year, Long bookStoreId) {
        br.saveBook(isbn, title, genre, year, bookStoreId);

    }

    @Override
    public void editBook(String oldIsbn,String isbn, String title, String genre, Integer year, Long bookStoreId)
    {
        Book book = br.listBooks().stream().filter(b->b.getIsbn().matches(oldIsbn)).findFirst().orElse(null);
        if(book!=null)
        {
            book.setTitle(title);
            book.setYear(year);
            book.setGenre(genre);
            book.setIsbn(isbn);
            BookStore bookStore = bsi.findAll().stream().filter(bs->bs.getId().equals(bookStoreId)).findFirst().orElse(null);
            System.out.println(bookStore.name);
            book.bookStore = bookStore;
        }
        else
        {
            saveBook(isbn,title,genre,year,bookStoreId);
        }
    }
    @Override
    public void deleteBook(String isbn)
    {
        br.delete(isbn);
    }

}
