package com.example.Reviewms.review.impl;


import com.example.Reviewms.review.Review;
import com.example.Reviewms.review.ReviewRepository;
import com.example.Reviewms.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews=reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReviews(Long companyId, Review review) {
        if(companyId!=null) {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        else
            return false;
    }

    @Override
    public Review getReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewId, Review updatedreview) {
        Review review=reviewRepository.findById(reviewId).orElse(null);
        if(review!=null){
            review.setCompanyId(updatedreview.getCompanyId());
            review.setDescription(updatedreview.getDescription());
            review.setTitle(updatedreview.getTitle());
            review.setRating(updatedreview.getRating());
            reviewRepository.save(updatedreview);
            return true;
        }else
            return false;

    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null) {
            reviewRepository.delete(review);
            return true;
        } else
            return false;
    }

}
