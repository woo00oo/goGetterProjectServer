package udodog.goGetterServer.model.dto.request.discussion;

import lombok.*;
import udodog.goGetterServer.model.entity.User;

@NoArgsConstructor
@Getter
public class DiscussionEditRequest {

    private Long id;
    private User user;
    private String title;
    private String content;

    @Builder
    public DiscussionEditRequest update (DiscussionEditRequest modBoard){

        this.id = modBoard.getId();
        this.user = modBoard.getUser();
        this.title = modBoard.getTitle();
        this.content = modBoard.getContent();

        return this;
    }
}
