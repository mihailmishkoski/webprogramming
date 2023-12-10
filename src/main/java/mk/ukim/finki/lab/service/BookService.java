package mk.ukim.finki.lab.service;

import mk.ukim.finki.lab.model.Author;
import mk.ukim.finki.lab.model.Book;

import java.util.List;

public interface BookService {
    List<Book> listBooks();
    Author addAuthorToBook(Author authorId, Book isbn);
    Book findBookByIsbn(String isbn);
    void saveBook(String isbn, String title, String genre, Integer year, Long bookStoreId);

    void editBook(String oldIsbn,String isbn, String title, String genre, Integer year, Long bookStoreId);
    void deleteBook(String isbn);
}
