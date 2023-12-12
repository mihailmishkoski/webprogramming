package mk.ukim.finki.lab.service.jpa;

import mk.ukim.finki.lab.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewJpa extends JpaRepository<Review, Long> {
}
