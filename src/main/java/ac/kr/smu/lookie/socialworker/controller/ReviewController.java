package ac.kr.smu.lookie.socialworker.controller;

import ac.kr.smu.lookie.socialworker.domain.Review;
import ac.kr.smu.lookie.socialworker.domain.User;
import ac.kr.smu.lookie.socialworker.dto.ReviewListDto;
import ac.kr.smu.lookie.socialworker.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
@Slf4j
public class ReviewController {
    private final ReviewService reviewService;
    private final int PAGE_SIZE = 25;

    @GetMapping
    public ResponseEntity<?> getReviewList(){
//        List<Review> list = reviewService.getReviewList();

//        Pageable pageable = PageRequest.of(page-1, PAGE_SIZE);
//
//        Page<Review> reviewList = reviewService.getReviewList(pageable);
//        PagedModel.PageMetadata pageMetadata = new PagedModel.PageMetadata(reviewList.getSize(), reviewList.getNumber(), reviewList.getTotalElements());
//        PagedModel<Review> body = PagedModel.of(reviewList.getContent(), pageMetadata);
//
//        body.add(linkTo(methodOn(ReviewController.class).getReviewList(page)).withSelfRel());
//        return ResponseEntity.ok(body);

        List<ReviewListDto> list = reviewService.getReviewList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> getReview(@PathVariable("reviewId") Long reviewId){
//        Review review = reviewService.getReview(reviewId);
//        EntityModel<Review> body = EntityModel.of(review);
//
//        body.add(linkTo(methodOn(ReviewController.class).getReview(reviewId)).withSelfRel());
//        return ResponseEntity.ok(body);
        Review review = reviewService.getReview(reviewId);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> postReview(@RequestBody Map<String, Object> map){
//        EntityModel<Review> body = EntityModel.of(reviewService.register(review));
//
//        body.add(linkTo(methodOn(ReviewController.class).postReview(review)).withSelfRel());
//        return new ResponseEntity<>(body, HttpStatus.CREATED);
        Review review = Review.builder().location(map.get("location").toString())
                .score((double)map.get("score")).terms((int)map.get("terms"))
                .workPlace(map.get("workplace").toString()).merits(map.get("merits").toString())
                .demerits(map.get("demerits").toString()).content(map.get("content").toString())
                .createdDate(LocalDateTime.now()).build();

        Map<String, Boolean> json = new HashMap<>();
        if(reviewService.register(review) != null){
            json.put("success",true);
        }else{
            json.put("success", false);
        }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<?> putReview(@PathVariable("reviewId") Long reviewId, @RequestBody Map<String, Object> map){
//        if(!user.equals(reviewService.getReview(review.getId()))){
//            return ResponseEntity.status(403).build();
//        }
//
//        EntityModel<Review> body = EntityModel.of(reviewService.update(review));
//
//        return ResponseEntity.ok(body);
        Review review = Review.builder().id(reviewId).location(map.get("location").toString())
                .score((double)map.get("score")).terms((int)map.get("terms"))
                .workPlace(map.get("workplace").toString()).merits(map.get("merits").toString())
                .demerits(map.get("demerits").toString()).content(map.get("content").toString())
                .build();
        Map<String, Boolean> json = new HashMap<>();
        if(reviewService.update(review) != null){
            json.put("success",true);
        }else{
            json.put("success", false);
        }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable("reviewId") Long reviewId){
//        if(user.equals(reviewService.getReview(reviewId)) || !user.getRoles().contains("ADMIN")){
//            return ResponseEntity.status(403).build();
//        }
//        return ResponseEntity.ok(reviewService.delete(reviewId));
        return new ResponseEntity<>(reviewService.delete(reviewId), HttpStatus.OK);
    }
}
