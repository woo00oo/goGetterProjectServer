package udodog.goGetterServer.model.entity;

import io.swagger.models.auth.In;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@DynamicInsert
public class DiscussionBoardReadhit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discussion_id")
    private DiscussionBoard discussionBoard;

    Integer count;

    @Builder
    public DiscussionBoardReadhit(DiscussionBoard discussionBoard, Integer count) {
        this.discussionBoard = discussionBoard;
        this.count = count;
    }

    public void incrementCount(){
        this.count++;
    }

    public DiscussionBoardReadhit createCount(DiscussionBoard board){
        this.discussionBoard = board;
        return this;
    }
}
