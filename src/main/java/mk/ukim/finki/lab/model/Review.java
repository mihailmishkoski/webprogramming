package mk.ukim.finki.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "review")
public class Review {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer score;
    private String description;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;


    public Review(Integer score, String description,LocalDateTime timestamp) {
        this.score = score;
        this.description = description;
        this.timestamp = timestamp;
    }

    public Review() {

    }





}

