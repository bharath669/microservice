package com.example.Reviewms.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class    ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId, @RequestBody Review review){
        boolean isReviewSaved=reviewService.addReviews(companyId,review);
        if (isReviewSaved) {
            return new ResponseEntity<>("Review added successfully", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Review Not Saved Successfully",HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId){
        return  new ResponseEntity<>(reviewService.getReview(reviewId),HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,@RequestBody Review review){
        boolean isUpdatedReview=reviewService.updateReview(reviewId,review);
        if (isUpdatedReview){
            return new ResponseEntity<>("review updated successfully",HttpStatus.OK);
        }else
            return new ResponseEntity<>("review not updated successfully",HttpStatus.NOT_FOUND);

    }
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
        boolean isDeletedReview= reviewService.deleteReview(reviewId);
        if (isDeletedReview){
            return new ResponseEntity<>("review deleted successfully",HttpStatus.OK);
        }else
            return new ResponseEntity<>("review not deleted successfully",HttpStatus.NOT_FOUND);
        }
}