package udodog.goGetterServer.model.dto.request.discussion;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "로그인 상태의 사용자의 정보")
    private User user;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @Builder
    public DiscussionBoard toEntity(DiscussionInsertRequestDto create){
        return DiscussionBoard.builder()
                .user(create.user)
                .title(create.title)
                .content(create.content)
                .build();
    }
}
