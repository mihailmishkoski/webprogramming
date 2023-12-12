package mk.ukim.finki.lab.repository;

import jakarta.transaction.Transactional;
import mk.ukim.finki.lab.bootstrap.DataHolder;
import mk.ukim.finki.lab.model.Author;
import mk.ukim.finki.lab.model.Book;
import mk.ukim.finki.lab.model.BookStore;
import mk.ukim.finki.lab.model.Review;
import mk.ukim.finki.lab.service.jpa.BookJpa;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {


    private final BookJpa bjpa;

    public BookRepository(BookJpa bjpa) {
        this.bjpa = bjpa;
    }

    public List<Book> listBooks()
    {
        return bjpa.findAll();
    }

    public Book findById(Long isbn) {
        return bjpa.findById(isbn).orElse(null);
    }
//    public Author addAuthorToBook(Author author, Book book){
//            book.authors.add(author);
//            return author;
//    }
//    @Transactional
//    public void saveBook(String isbn, String title, String genre, Integer year, Long bookSToreId){
//        Book book = new Book(isbn,title,genre,year,new ArrayList<>());
//        BookStore bookStore = bsi.findById(bookSToreId.toString()).orElse(null);
//        DataHolder.books.add(book);
//        book.bookStore.add(bookStore);
//        bsi.save(bookStore);
//        bi.save(book);
//
//    }
//    @Transactional
//    public void addReview(Book b, Review review)
//    {
//        b.reviews.add(review);
//        bi.save(b);
//
//    }
//    @Transactional
//    public void delete(String isbn)
//    {
//        Book book = bi.findByIsbn(isbn).orElse(null);
//        if (book != null) {
//            bi.delete(book);
//        }
//    }
    
}
