package com.fabhotel.fabhotel.Controller;

import com.fabhotel.fabhotel.Entity.PropertyUser;
import com.fabhotel.fabhotel.Entity.Review;
import com.fabhotel.fabhotel.Service.ReviewService;
import org.apache.http.ssl.PrivateKeyStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Review")

public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;

    }

    @PostMapping("/addreview/{id}")
    public ResponseEntity<String>addreview(@RequestBody Review review,
                                     @AuthenticationPrincipal PropertyUser user,
                                     @PathVariable Long id ){
        String addreview = reviewService.addreview(review, user, id);
return  new ResponseEntity<>("save review", HttpStatus.CREATED);
    }
@GetMapping("/getallreview")
    public ResponseEntity<List<Review>>getallreview(){
    List<Review> getallreview = reviewService.getallreview();
    return  new ResponseEntity<>(getallreview,HttpStatus.OK);

}
@GetMapping("/Review/{id}")
public ResponseEntity<List<Review>>findbyid(@PathVariable Long id){
    List<Review> review = reviewService.findbyid(id);
    return new ResponseEntity<>(review,HttpStatus.OK);

}
}
