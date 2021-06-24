package udodog.goGetterServer.model.dto.request.discussion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import udodog.goGetterServer.model.entity.DiscussionBoard;
import udodog.goGetterServer.model.entity.User;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class DiscussionInsertRequestDto {

    @NotNull
    private User user;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @Builder
    public DiscussionBoard toEntity(){
        return DiscussionBoard.builder()
                .user(user)
                .title(title)
                .content(content)
                .build();
    }
}
