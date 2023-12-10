package mk.ukim.finki.lab.model;
import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.lab.model.convertors.AuthorFullNameConvertor;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "author")
public class Author {

    @Id
    public Long id;


    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;


    @Convert(converter = AuthorFullNameConvertor.class)
    private AuthorFullName fullname;


    public String biography;

    public Author(Long id, String name, String surname, String biography, LocalDate dateOfBirth) {
        this.id = id;
        this.biography = biography;
        this.fullname = new AuthorFullName(name,surname);
        this.dateOfBirth = dateOfBirth;
    }
    public Author(){}


}
