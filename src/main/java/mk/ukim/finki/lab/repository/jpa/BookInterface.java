package mk.ukim.finki.lab.repository.jpa;

import mk.ukim.finki.lab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookInterface extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);
}
