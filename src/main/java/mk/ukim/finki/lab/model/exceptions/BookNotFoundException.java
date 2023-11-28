package mk.ukim.finki.lab.model.exceptions;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String isbn) {
        super("Book not found with ISBN: " + isbn);
    }
}
