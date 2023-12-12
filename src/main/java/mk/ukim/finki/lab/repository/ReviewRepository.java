package mk.ukim.finki.lab.repository;

import mk.ukim.finki.lab.model.Review;
import mk.ukim.finki.lab.service.jpa.ReviewJpa;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepository {
    private final ReviewJpa ri;

    public ReviewRepository(ReviewJpa ri) {
        this.ri = ri;
    }

}
