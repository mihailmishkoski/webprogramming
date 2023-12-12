package mk.ukim.finki.lab.repository;

import mk.ukim.finki.lab.model.Author;
import mk.ukim.finki.lab.service.jpa.AuthorJpa;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository  {
    private final AuthorJpa ai;

    public AuthorRepository(AuthorJpa ai) {
        this.ai = ai;
    }

    public List<Author> listAuthors()
    {
        return ai.findAll();
    }
    public Optional<Author> findById(Long id) {
        return ai.findById(id);
    }


//    @Transactional
//    public void deleteReviewByIsbn(String isbn)
//    {
//        ai.findById(isbn);
//    }
}
