package mk.ukim.finki.lab.service.jpa;

import mk.ukim.finki.lab.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorJpa extends JpaRepository<Author, Long> {

}
