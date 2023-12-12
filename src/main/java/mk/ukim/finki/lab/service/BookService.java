package mk.ukim.finki.lab.service;

import mk.ukim.finki.lab.model.Author;
import mk.ukim.finki.lab.model.Book;
import mk.ukim.finki.lab.model.Review;

import java.util.List;

public interface BookService {
    List<Book> listBooks();
    Author addAuthorToBook(Author authorId, Book isbn);
    Book findBookById(Long Id);
    void saveBook(String isbn, String title, String genre, Integer year, Long bookStoreId);

    void editBook(Long oldIsbn,String isbn, String title, String genre, Integer year, Long bookStoreId);

    List<Author> getAuthorsForBook(Long isbn);

    void addReview(Book book, Review review);

    void deleteBook(Long isbn);


}
