package mk.ukim.finki.lab.repository;

import mk.ukim.finki.lab.bootstrap.DataHolder;
import mk.ukim.finki.lab.model.Author;
import mk.ukim.finki.lab.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {
    public List<Book> listBooks()
    {
        return DataHolder.books;
    }

    public Book findByIsbn(String isbn) {
        return DataHolder.books.stream().filter(b-> b.getIsbn().equals(isbn)).findFirst().orElse(null);
    }
    public Author addAuthorToBook(Author author, Book book){
        if (author != null && book != null) {
            book.getAuthors().add(author); // Assuming you have a method to get the authors of a book
        }
        return author;
    }

}
