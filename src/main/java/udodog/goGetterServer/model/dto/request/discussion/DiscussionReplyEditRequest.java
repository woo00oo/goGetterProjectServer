package udodog.goGetterServer.model.dto.request.discussion;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class DiscussionReplyEditRequest {

    @NotNull
    private Long userId;

    @NotNull
    private String content;

    public DiscussionReplyEditRequest(Long userId, String content) {
        this.userId = userId;
        this.content = content;
    }

}
