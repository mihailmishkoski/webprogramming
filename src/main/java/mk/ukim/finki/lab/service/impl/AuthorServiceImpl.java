package mk.ukim.finki.lab.service.impl;

import mk.ukim.finki.lab.model.Author;
import mk.ukim.finki.lab.repository.AuthorRepository;
import mk.ukim.finki.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository ar)
    {
        this.authorRepository = ar;
    }
    @Override
    public List<Author> listAuthors() {
        return authorRepository.listAuthors();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }
}
