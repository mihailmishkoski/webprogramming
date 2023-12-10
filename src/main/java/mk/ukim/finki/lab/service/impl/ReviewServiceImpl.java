package mk.ukim.finki.lab.service.impl;

import mk.ukim.finki.lab.model.Book;
import mk.ukim.finki.lab.model.Review;
import mk.ukim.finki.lab.repository.ReviewRepository;
import mk.ukim.finki.lab.service.ReviewService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void saveReview(Integer score, String description, Book book, LocalDateTime timestamp) {
        reviewRepository.save(new Review(score,description,book,timestamp));
    }
}
