package udodog.goGetterServer.model.dto.response.discussion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import udodog.goGetterServer.model.entity.DiscussionBoardReply;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class DiscussionReplyResponse {

    private Long id;               // 댓글 번호
    private String userNickName;   // 유저 닉네임
    private String content;        // 댓글 내용
    private LocalDate createAt;    // 댓글 등록날짜

    public DiscussionReplyResponse(DiscussionBoardReply boardReply){

        this.id = boardReply.getId();
        this.userNickName = boardReply.getUser().getNickName();
        this.content = boardReply.getContent();
        this.createAt = boardReply.getCreateAt();
    }
}
