package mk.ukim.finki.lab.service.impl;

import mk.ukim.finki.lab.model.Author;
import mk.ukim.finki.lab.model.Book;
import mk.ukim.finki.lab.model.BookStore;
import mk.ukim.finki.lab.model.Review;
import mk.ukim.finki.lab.repository.BookRepository;
import mk.ukim.finki.lab.service.BookStoreService;
import mk.ukim.finki.lab.service.jpa.BookJpa;
import mk.ukim.finki.lab.service.jpa.BookStoreJpa;
import mk.ukim.finki.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository br;

    private final BookJpa bjpa;
    private final BookStoreJpa bsjpa;


    private final BookStoreService bss;

    public BookServiceImpl(BookRepository br, BookStoreServiceImpl bsi, BookJpa bjpa, BookStoreJpa bsjpa, BookStoreService bss) {
        this.br = br;
        this.bjpa = bjpa;
        this.bsjpa = bsjpa;
        this.bss = bss;
    }

    @Override
    public List<Book> listBooks() {
        return br.listBooks();
    }

    @Override
    public Author addAuthorToBook(Author author, Book book) {
            book.authors.add(author);
            return author;
    }

    @Override
    public Book findBookById(Long id) {
        return br.findById(id);
    }
    @Override
    public List<Author> getAuthorsForBook(Long id) {
        Book book = br.findById(id);
        return book != null ? book.getAuthors() : Collections.emptyList();
    }

    @Override
    public void saveBook(String isbn, String title, String genre, Integer year, Long bookStoreId) {
        Book book = new Book(isbn,title,genre,year,new ArrayList<>());
        BookStore bookStore = bss.findById(bookStoreId);
        book.bookStore = bookStore;
        bss.saveBookStore(bookStore);
        bjpa.save(book);

    }

    @Override
    public void addReview(Book book, Review review)
    {
        book.reviews.add(review);
        bjpa.save(book);
    }

    @Override
    public void editBook(Long bookId,String isbn, String title, String genre, Integer year, Long bookStoreId)
    {
        Book book = br.listBooks().stream().filter(b->b.getId()==(bookId)).findFirst().orElse(null);
        book.isbn = isbn;
        book.title = title;
        book.genre = genre;
        book.year = year;
        book.bookStore = bss.findById(bookStoreId);
        bjpa.save(book);
    }

    @Override
    public void deleteBook(Long isbn)
    {
        Book book = br.listBooks().stream().filter(b->b.getId()==isbn).findFirst().orElse(null);
        if(book!=null)
        {
            bjpa.delete(book);
        }
    }

}
