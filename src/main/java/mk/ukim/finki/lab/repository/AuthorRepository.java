package mk.ukim.finki.lab.repository;

import mk.ukim.finki.lab.bootstrap.DataHolder;
import mk.ukim.finki.lab.model.Author;
import mk.ukim.finki.lab.service.AuthorService;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository  {
    public List<Author> listAuthors()
    {
        return DataHolder.authors;
    }
    public Optional<Author> findById(Long id) {
        return DataHolder.authors.stream().filter(a -> a.getId().equals(id)).findFirst();
    }
}
