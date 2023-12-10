package mk.ukim.finki.lab.repository.jpa;

import mk.ukim.finki.lab.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewInterface extends JpaRepository<Review, Long> {
}
