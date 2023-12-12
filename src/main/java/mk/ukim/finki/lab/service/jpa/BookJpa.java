package mk.ukim.finki.lab.service.jpa;

import mk.ukim.finki.lab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookJpa extends JpaRepository<Book, Long> {


}
