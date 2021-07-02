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


    private Long id;                 // 게시판 번호
    private String userNickName;     // 유저 닉네임
    private String title;            // 게시판 제목
    private String content;          // 게시판 내용
    private String profileUrl;       // 유저 프로필
    private LocalDate createAt;      // 게시판 등록일

    public DiscussionDetailResponse(DiscussionBoard discussionBoard){
        this.id = discussionBoard.getId();
        this.userNickName = discussionBoard.getUser().getNickName();
        this.title = discussionBoard.getTitle();
        this.content = discussionBoard.getContent();
        this.profileUrl = discussionBoard.getUser().getProfileUrl();
        this.createAt = discussionBoard.getCreateAt();
    }
}
