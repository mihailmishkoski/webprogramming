package mk.ukim.finki.lab.repository;

import jakarta.transaction.Transactional;
import mk.ukim.finki.lab.bootstrap.DataHolder;
import mk.ukim.finki.lab.model.Author;
import mk.ukim.finki.lab.model.Book;
import mk.ukim.finki.lab.model.BookStore;
import mk.ukim.finki.lab.repository.jpa.AuthorInterface;
import mk.ukim.finki.lab.repository.jpa.BookInterface;
import mk.ukim.finki.lab.repository.jpa.BookStoreInterface;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {
    private final BookInterface bi;
    private final BookStoreInterface bsi;

    private final AuthorInterface ai;

    public BookRepository(BookInterface bi, BookStoreInterface bsi,AuthorInterface ai) {
        this.bi = bi;
        this.bsi = bsi;
        this.ai = ai;
    }

    public List<Book> listBooks()
    {
        return bi.findAll();
    }

    public Book findByIsbn(String isbn) {
        Book book = bi.findByIsbn(isbn).orElse(null);
        System.out.println("eve sea");
        System.out.println(book);
        return book;
    }
    public Author addAuthorToBook(Author author, Book book){
            book.authors.add(author);
            return author;
    }
    @Transactional
    public void saveBook(String isbn, String title, String genre, Integer year, Long bookSToreId){
        Book book = new Book(isbn,title,genre,year,new ArrayList<>());
        BookStore bookStore = bsi.findById(bookSToreId.toString()).orElse(null);
        DataHolder.books.add(book);
        book.bookStore = bookStore;
        bsi.save(bookStore);
        bi.save(book);

    }
    @Transactional
    public void delete(String isbn)
    {
        Book book = bi.findByIsbn(isbn).orElse(null);
        if (book != null) {
            bi.delete(book);
        }
    }
    
}
