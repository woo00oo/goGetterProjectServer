package udodog.goGetterServer.model.dto.request.discussion;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class DiscussionReplyEditRequest {

    @NotNull
    private String content;    // 댓글 내용

}
