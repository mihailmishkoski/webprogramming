package mk.ukim.finki.lab.repository.jpa;

import mk.ukim.finki.lab.model.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookStoreInterface extends JpaRepository<BookStore, String> {
}
