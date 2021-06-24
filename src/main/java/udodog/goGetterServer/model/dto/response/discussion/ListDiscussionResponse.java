package udodog.goGetterServer.model.dto.response.discussion;

import lombok.*;
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
    private LocalDate creatAt;

}
