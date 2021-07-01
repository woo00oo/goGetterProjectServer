package udodog.goGetterServer.model.dto.response.discussion;

import lombok.Getter;
import lombok.NoArgsConstructor;
import udodog.goGetterServer.model.entity.DiscussionBoard;
import udodog.goGetterServer.model.entity.DiscussionBoardReply;
import udodog.goGetterServer.model.entity.User;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class DiscussionReplyResponse {

    private Long id;

    private String userNickName;

    private String content;

    private LocalDate createAt;

    public DiscussionReplyResponse(DiscussionBoardReply boardReply){

        this.id = boardReply.getId();
        this.userNickName = boardReply.getUser().getNickName();
        this.content = boardReply.getContent();
        this.createAt = boardReply.getCreateAt();
    }
}
