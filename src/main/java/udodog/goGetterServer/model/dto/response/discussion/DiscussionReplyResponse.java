package udodog.goGetterServer.model.dto.response.discussion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DiscussionReplyResponse {

    private Long id;                // 댓글 번호
    private String userNickName;    // 유저 닉네임
    private String content;         // 댓글 내용
    private LocalDate createAt;     // 댓글 작성일

}
