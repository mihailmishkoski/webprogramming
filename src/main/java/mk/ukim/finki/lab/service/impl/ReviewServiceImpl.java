package mk.ukim.finki.lab.service.impl;

import mk.ukim.finki.lab.model.Book;
import mk.ukim.finki.lab.model.Review;
import mk.ukim.finki.lab.repository.BookRepository;
import mk.ukim.finki.lab.repository.ReviewRepository;
import mk.ukim.finki.lab.service.BookService;
import mk.ukim.finki.lab.service.ReviewService;
import mk.ukim.finki.lab.service.jpa.ReviewJpa;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReviewServiceImpl implements ReviewService {



    private final ReviewJpa rjpa;

    private final BookService bs;


    public ReviewServiceImpl(ReviewJpa rjpa,BookService bs) {
        this.rjpa = rjpa;
        this.bs = bs;
    }

    @Override
    public void saveReview(Integer score, String description, Book book, LocalDateTime timestamp) {
        Review review = new Review(score,description,timestamp);
        review.setBook(book);
        book.reviews.add(review);
        rjpa.save(review);

    }

}
