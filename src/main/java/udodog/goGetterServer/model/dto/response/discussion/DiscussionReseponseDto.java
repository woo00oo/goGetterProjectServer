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

    private Long id;
    private String userNickname;
    private String title;
    private LocalDate createAt;

    private Integer readHit;


    public DiscussionReseponseDto(DiscussionReseponseDto reseponseDto, Optional<DiscussionBoardReadhit> readhit) {
        this.id = reseponseDto.getId();
        this.userNickname = reseponseDto.getUserNickname();
        this.title = reseponseDto.getTitle();
        this.createAt = reseponseDto.getCreateAt();
        this.readHit = readhit.get().getCount();
    }

}