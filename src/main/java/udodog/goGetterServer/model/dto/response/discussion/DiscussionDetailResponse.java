package udodog.goGetterServer.model.dto.response.discussion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class DiscussionDetailResponse {


    private Long id;                 // 게시판 번호
    private Long userId;             // 유저 번호
    private String userNickName;     // 유저 닉네임
    private String title;            // 게시판 제목
    private String content;          // 게시판 내용
    private LocalDate createAt;      // 게시판 등록일


    public DiscussionDetailResponse(Optional<DiscussionDetailResponse> discussionBoard) {

        this.id = discussionBoard.get().getId();
        this.userId = discussionBoard.get().getUserId();
        this.userNickName = discussionBoard.get().getUserNickName();
        this.title = discussionBoard.get().getTitle();
        this.content = discussionBoard.get().getContent();
        this.createAt = discussionBoard.get().getCreateAt();

    }
}
