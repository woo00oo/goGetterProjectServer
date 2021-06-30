package udodog.goGetterServer.model.dto.response.discussion;

import com.fasterxml.jackson.annotation.OptBoolean;
import lombok.*;
import udodog.goGetterServer.model.entity.DiscussionBoard;
import udodog.goGetterServer.model.entity.DiscussionBoardReadhit;

import java.time.LocalDate;
import java.util.Optional;

@NoArgsConstructor
@Getter
public class DiscussionReseponseDto {

    private Long id;
    private String userNickname;
    private String title;
    private LocalDate createAt;

    // 조회 수
    private int readHit;

    public DiscussionReseponseDto(DiscussionBoard discussionBoard, Optional<DiscussionBoardReadhit> readhit) {
        this.id = discussionBoard.getId();
        this.userNickname = discussionBoard.getUser().getNickName();
        this.title = discussionBoard.getTitle();
        this.createAt = discussionBoard.getCreateAt();
        this.readHit = readhit.get().getCount();

    }
}