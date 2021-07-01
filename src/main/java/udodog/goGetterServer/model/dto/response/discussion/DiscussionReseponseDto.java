package udodog.goGetterServer.model.dto.response.discussion;

import lombok.Getter;
import lombok.NoArgsConstructor;
import udodog.goGetterServer.model.entity.DiscussionBoard;
import udodog.goGetterServer.model.entity.DiscussionBoardReadhit;

import java.time.LocalDate;
import java.util.Optional;

@NoArgsConstructor
@Getter
public class DiscussionReseponseDto {

    private Long id;                // 게시판 번호
    private String userNickname;    // 유저 닉네임
    private String title;           // 게시판 제목
    private LocalDate createAt;     // 게시판 등록 날짜
    private Integer readHit;        // 조회 수

    public DiscussionReseponseDto(DiscussionBoard discussionBoard, Optional<DiscussionBoardReadhit> readhit) {
        this.id = discussionBoard.getId();
        this.userNickname = discussionBoard.getUser().getNickName();
        this.title = discussionBoard.getTitle();
        this.createAt = discussionBoard.getCreateAt();
        this.readHit = readhit.get().getCount();

    }
}