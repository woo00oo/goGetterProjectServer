package udodog.goGetterServer.model.dto.request.discussion;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import udodog.goGetterServer.model.entity.DiscussionBoard;
import udodog.goGetterServer.model.entity.User;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@NoArgsConstructor
@Getter
public class DiscussionInsertRequestDto {

    @NotNull
    private String title;

    @NotNull
    private String content;

    @Builder
    public DiscussionBoard toEntity(DiscussionInsertRequestDto create, Optional<User> user){
        return DiscussionBoard.builder()
                .user(user.get())
                .title(create.title)
                .content(create.content)
                .build();
    }
}
