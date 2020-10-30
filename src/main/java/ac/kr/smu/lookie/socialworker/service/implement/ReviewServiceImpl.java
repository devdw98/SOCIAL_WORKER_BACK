package ac.kr.smu.lookie.socialworker.service.implement;

import ac.kr.smu.lookie.socialworker.domain.Review;
import ac.kr.smu.lookie.socialworker.dto.ReviewListDto;
import ac.kr.smu.lookie.socialworker.repository.ReviewRepository;
import ac.kr.smu.lookie.socialworker.service.CheckSuccessDeleteService;
import ac.kr.smu.lookie.socialworker.service.FileService;
import ac.kr.smu.lookie.socialworker.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final FileService fileService;
    private final CheckSuccessDeleteService deleteService;

//    @Override
//    public Page<Review> getReviewList(Pageable pageable) {
//        return reviewRepository.findAll(pageable);
//    }

    @Override
    public List<ReviewListDto> getReviewList(){
        List<ReviewListDto> list = new ArrayList<>();
        List<Review> origin = reviewRepository.findAll();
        for(Review r : origin){
            list.add(ReviewListDto.builder()
                    .id(r.getId()).location(r.getLocation())
            .score(r.getScore()).term(r.getTerms()).build());
        }
        return list;
    }

    @Override
    public Review getReview(Long id) {
        Review review = reviewRepository.findById(id).orElse(null);
        return review;
    }

    @Override
    public Review register(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review update(Review review) {
        Review origin = reviewRepository.getOne(review.getId());
        origin.update(review);
        return reviewRepository.save(origin);
    }

    @Override
    @Transactional
    public Map<String, Boolean> delete(Long id) {
        Map<String, Boolean> result;
        try{
            fileService.deleteByReview(Review.builder().id(id).build());
            result = deleteService.delete(reviewRepository, id);
        }catch(Exception e){
            result = new HashMap<>();
            result.put("success", false);
        }
        return result;
    }
}
