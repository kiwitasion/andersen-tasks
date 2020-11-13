package ru.kivit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kivit.models.Review;
import ru.kivit.repositories.ReviewRepository;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository repository;

    @Autowired
    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public Review findById(Long id){
        return repository.getOne(id);
    }

    public List<Review> findAll(){

        return repository.findAll();
    }

    public Review save(Review review){
        return repository.save(review);
    }

    public Review update(Review newReview, Long id){
        Review oldReview = repository.findById(id).orElse(null);
        oldReview.setText(newReview.getText());
        return  repository.save(oldReview);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
