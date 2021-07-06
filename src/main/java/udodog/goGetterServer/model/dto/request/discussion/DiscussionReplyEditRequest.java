package udodog.goGetterServer.model.dto.request.discussion;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class DiscussionReplyEditRequest {

    @NotNull
    private String content;     // 댓글 내용

    private LocalDate createAt = LocalDate.now();         // 댓글 수정일

}
