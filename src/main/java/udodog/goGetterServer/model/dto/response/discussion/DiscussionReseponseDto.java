package udodog.goGetterServer.model.dto.response.discussion;

import lombok.*;
import udodog.goGetterServer.model.entity.DiscussionBoard;
import udodog.goGetterServer.model.entity.User;

import java.time.LocalDate;

@Getter
public class DiscussionReseponseDto {

    private Long id;
    private User user;
    private String title;
    private LocalDate creatAt;

    @Builder
    public DiscussionReseponseDto(DiscussionBoard discussionBoard){
        this.id = discussionBoard.getId();
        this.user = discussionBoard.getUser();
        this.title = discussionBoard.getTitle();
        this.creatAt = discussionBoard.getCreateAt();
    }
}
