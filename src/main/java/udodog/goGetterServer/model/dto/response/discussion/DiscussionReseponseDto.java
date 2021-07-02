package udodog.goGetterServer.model.dto.response.discussion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import udodog.goGetterServer.model.entity.DiscussionBoardReadhit;

import java.time.LocalDate;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DiscussionReseponseDto {

    private Long id;                // 게시판 번호
    private String userNickname;    // 유저 닉네임
    private String title;           // 게시판 제목
    private LocalDate createAt;     // 게시판 등록일

    private Integer readHit;        // 조회수


    public DiscussionReseponseDto(DiscussionReseponseDto reseponseDto, Optional<DiscussionBoardReadhit> readhit) {
        this.id = reseponseDto.getId();
        this.userNickname = reseponseDto.getUserNickname();
        this.title = reseponseDto.getTitle();
        this.createAt = reseponseDto.getCreateAt();
        this.readHit = readhit.get().getCount();
    }

}