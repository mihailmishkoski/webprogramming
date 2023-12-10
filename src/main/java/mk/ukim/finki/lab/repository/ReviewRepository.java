package mk.ukim.finki.lab.repository;

import mk.ukim.finki.lab.model.Review;
import mk.ukim.finki.lab.repository.jpa.ReviewInterface;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepository {
    private final ReviewInterface ri;

    public ReviewRepository(ReviewInterface ri) {
        this.ri = ri;
    }
    public void save(Review r)
    {
        ri.save(r);
    }
}
