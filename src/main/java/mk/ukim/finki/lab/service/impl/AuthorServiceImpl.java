package mk.ukim.finki.lab.service.impl;

import mk.ukim.finki.lab.model.Author;
import mk.ukim.finki.lab.repository.AuthorRepository;
import mk.ukim.finki.lab.service.AuthorService;
import mk.ukim.finki.lab.service.jpa.AuthorJpa;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    private final AuthorJpa ajpa;
    public AuthorServiceImpl(AuthorJpa ajpa,AuthorRepository authorRepository)
    {
        this.ajpa = ajpa;
        this.authorRepository = authorRepository;
    }
    @Override
    public List<Author> listAuthors() {
        return authorRepository.listAuthors();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public void saveAuthor(String name, String surname, String biography, LocalDate dof) {
        ajpa.save(new Author(name,surname,biography,dof));
    }




}
