package mk.ukim.finki.lab.repository.jpa;

import mk.ukim.finki.lab.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorInterface extends JpaRepository<Author, Long> {
}
