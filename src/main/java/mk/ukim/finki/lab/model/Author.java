package mk.ukim.finki.lab.model;
import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.lab.model.convertors.AuthorFullNameConvertor;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.List;


@Data
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Convert(converter = AuthorFullNameConvertor.class)
    private AuthorFullName fullname;
    public String biography;
    @ManyToMany
    public List<Book> books;
    public Author(String name, String surname, String biography, LocalDate dateOfBirth) {
        this.biography = biography;
        this.fullname = new AuthorFullName(name,surname);
        this.dateOfBirth = dateOfBirth;
    }
    public Author(){}


}
