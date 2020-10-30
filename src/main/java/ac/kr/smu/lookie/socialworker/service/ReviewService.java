package ac.kr.smu.lookie.socialworker.service;

import ac.kr.smu.lookie.socialworker.domain.Review;
import ac.kr.smu.lookie.socialworker.dto.ReviewListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ReviewService {

//    public Page<Review> getReviewList(Pageable pageable);
    public List<ReviewListDto> getReviewList();
    public Review getReview(Long id);
    public Review register(Review review);
    public Review update(Review review);
    public Map<String, Boolean> delete(Long id);
}
