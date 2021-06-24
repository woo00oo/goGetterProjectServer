package udodog.goGetterServer.model.dto.response.discussion;

import lombok.*;
import udodog.goGetterServer.model.entity.DiscussionBoard;
import udodog.goGetterServer.model.entity.User;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ListDiscussionResponse {

    private Long id;
    private User user;
    private String title;
    private String content;
    private LocalDate creatAt;

    public DiscussionBoard toEntity() {
        return DiscussionBoard.builder()
                .user(user)
                .title(title)
                .content(content)
                .build();
    }
}
