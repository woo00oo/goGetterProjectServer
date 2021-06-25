package udodog.goGetterServer.model.dto.response.discussion;

import lombok.*;
import udodog.goGetterServer.model.entity.DiscussionBoard;
import udodog.goGetterServer.model.entity.User;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class DiscussionReseponseDto {

    private Long id;
    private String userNickname;
    private String title;
    private LocalDate createAt;

    public DiscussionReseponseDto(DiscussionBoard discussionBoard) {
        this.id = discussionBoard.getId();
        this.userNickname = discussionBoard.getUser().getNickName();
        this.title = discussionBoard.getTitle();
        this.createAt = discussionBoard.getCreateAt();
    }

}