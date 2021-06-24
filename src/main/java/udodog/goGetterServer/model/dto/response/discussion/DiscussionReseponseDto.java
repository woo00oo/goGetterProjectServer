package udodog.goGetterServer.model.dto.response.discussion;

import lombok.*;
import udodog.goGetterServer.model.entity.DiscussionBoard;
import udodog.goGetterServer.model.entity.User;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@ToString
public class DiscussionReseponseDto {

    private Long id;
    private User user;
    private String title;
    private LocalDate createAt;

    @Builder
    public DiscussionReseponseDto(Long id, User user, String title, LocalDate createAt){
        this.id = id;
        this.user = user;
        this.title = title;
        this.createAt = createAt;
    }

}
