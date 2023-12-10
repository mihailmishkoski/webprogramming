package mk.ukim.finki.lab.repository;

import mk.ukim.finki.lab.bootstrap.DataHolder;
import mk.ukim.finki.lab.model.Author;
import mk.ukim.finki.lab.repository.jpa.AuthorInterface;
import mk.ukim.finki.lab.service.AuthorService;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository  {
    private final AuthorInterface ai;

    public AuthorRepository(AuthorInterface ai) {
        this.ai = ai;
    }

    public List<Author> listAuthors()
    {
        return ai.findAll();
    }
    public Optional<Author> findById(Long id) {
        //return DataHolder.authors.stream().filter(a -> a.getId().equals(id)).findFirst();
        return ai.findById(id);
    }
    public void save(Author a)
    {
        ai.save(a);
        DataHolder.authors.add(a);
    }
}
