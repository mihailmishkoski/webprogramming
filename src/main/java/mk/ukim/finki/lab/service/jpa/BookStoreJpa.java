package mk.ukim.finki.lab.service.jpa;

import mk.ukim.finki.lab.model.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookStoreJpa extends JpaRepository<BookStore, Long> {

}
