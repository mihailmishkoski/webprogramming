package mk.ukim.finki.lab.service;

import mk.ukim.finki.lab.model.Author;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> listAuthors();

    Optional<Author> findById(Long id);

    void saveAuthor(String name, String surname, String biography, LocalDate dof);
//    void deleteReviewByIsbn(String isbn);

}
