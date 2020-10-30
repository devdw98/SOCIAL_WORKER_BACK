package ac.kr.smu.lookie.socialworker.dto;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewListDto {
        private Long id;
        private String location;
        private int term;
        private double score;
}
