package udodog.goGetterServer.model.dto.response.sharingboard;

import lombok.*;
import udodog.goGetterServer.model.entity.User;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimpleBoardResponse {

    private Long id;

    private User user;

    private String title;

    private LocalDate createdAt;

    // 댓글 수
    private Integer replyCnt;

    // 좋아요 수
    private Integer likeCnt;
}
