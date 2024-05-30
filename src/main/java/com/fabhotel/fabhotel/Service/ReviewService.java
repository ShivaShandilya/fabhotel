package com.fabhotel.fabhotel.Service;

import com.fabhotel.fabhotel.Entity.Property;
import com.fabhotel.fabhotel.Entity.PropertyUser;
import com.fabhotel.fabhotel.Entity.Review;
import com.fabhotel.fabhotel.Repository.PropertyRepository;
import com.fabhotel.fabhotel.Repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;

    private PropertyRepository propertyRepository;
    public ReviewService(ReviewRepository reviewRepository, PropertyRepository propertyRepository) {
        this.reviewRepository = reviewRepository;


        this.propertyRepository = propertyRepository;
    }

    public String addreview(Review review, PropertyUser user, Long id) {
        Property property = propertyRepository.findById(id).get();
        review.setProperty(property);
        review.setPropertyUser(user);
        reviewRepository.save(review);
        return null;


    }

    public List<Review> getallreview() {
        List<Review> all = reviewRepository.findAll();
        return all;
    }

    public List<Review> findbyid(Long id) {
        Property property = propertyRepository.findById(id).get();
        List<Review> byproperty = reviewRepository.findReviewByproperty(property);
        return byproperty;


    }
}
