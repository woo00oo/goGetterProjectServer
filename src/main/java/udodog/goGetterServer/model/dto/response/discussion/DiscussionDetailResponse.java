package udodog.goGetterServer.model.dto.response.discussion;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import udodog.goGetterServer.model.entity.DiscussionBoard;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@ToString
public class DiscussionDetailResponse {

    private Long id;
    private String userNickname;
    private String title;
    private String content;
    private String profileUrl;
    private LocalDate createAt;

    public DiscussionDetailResponse(DiscussionBoard discussionBoard){
        this.id = discussionBoard.getId();
        this.userNickname = discussionBoard.getUser().getNickName();
        this.title = discussionBoard.getTitle();
        this.content = discussionBoard.getContent();
        this.profileUrl = discussionBoard.getUser().getProfileUrl();
        this.createAt = discussionBoard.getCreateAt();
    }
}
