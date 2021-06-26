package udodog.goGetterServer.model.dto.request.discussion;

import lombok.Getter;
import lombok.NoArgsConstructor;
import udodog.goGetterServer.model.entity.DiscussionBoard;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class DiscussionEditRequest {

    @NotNull
    private String title;

    @NotNull
    private String content;

    public DiscussionEditRequest (DiscussionBoard modBoard){

        this.title = modBoard.getTitle();
        this.content = modBoard.getContent();

    }

}
