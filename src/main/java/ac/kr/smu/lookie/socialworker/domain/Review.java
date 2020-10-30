package ac.kr.smu.lookie.socialworker.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String location;
    @Column
    private double score;
    @Column
    private int terms;
    @Column
    private String workPlace;
    @Column
    private String merits;
    @Column
    private String demerits;
    @Column
    private String content;
    @Column
    private LocalDateTime createdDate;
    @Column
    private LocalDateTime modifyDate;
//    @Column
//    @Enumerated(value = EnumType.STRING)
//    private UserRole role;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "review")
//    @Builder.Default
//    private List<FileInfo> fileList = new ArrayList<>();

    public void update(Review review){
        this.merits = review.getMerits();
        this.demerits = review.getDemerits();
        this.content = review.getContent();
        this.modifyDate = LocalDateTime.now();
    }
}
