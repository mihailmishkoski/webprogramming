package mk.ukim.finki.lab.service;

import mk.ukim.finki.lab.model.Book;

import java.time.LocalDateTime;

public interface ReviewService {
    void saveReview(Integer score, String description, Book book, LocalDateTime timestamp);

}
