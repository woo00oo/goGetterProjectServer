package udodog.goGetterServer.model.dto.request.discussion;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class DiscussionReplyEditRequest {

    @NotNull
    Long userId;

    @NotNull
    private String content;

}
