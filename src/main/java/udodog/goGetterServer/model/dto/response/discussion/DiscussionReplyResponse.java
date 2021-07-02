package udodog.goGetterServer.model.dto.response.discussion;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class DiscussionReplyResponse {

    private Long id;

    private String userNickName;

    private String content;

    private LocalDate createAt;

    public DiscussionReplyResponse(DiscussionReplyResponse boardReply){

        this.id = boardReply.getId();
        this.userNickName = boardReply.getUserNickName();
        this.content = boardReply.getContent();
        this.createAt = boardReply.getCreateAt();
    }
}
