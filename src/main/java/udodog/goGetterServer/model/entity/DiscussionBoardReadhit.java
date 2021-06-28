package udodog.goGetterServer.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class DiscussionBoardReadhit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discussion_id")
    private DiscussionBoard discussionBoard;

    int count;

    @Builder
    public DiscussionBoardReadhit(DiscussionBoard discussionBoard, int count) {
        this.discussionBoard = discussionBoard;
        this.count = count;
    }
}
