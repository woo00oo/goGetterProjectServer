package udodog.goGetterServer.model.dto.request.discussion;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import udodog.goGetterServer.model.entity.DiscussionBoard;
import udodog.goGetterServer.model.entity.DiscussionBoardReply;
import udodog.goGetterServer.model.entity.User;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class DiscussionReplyInsertRequest {

    @NotNull
    @ApiModelProperty(value = "현재 게시판 정보")
    private DiscussionBoard discussionBoard;

    @NotNull
    @ApiModelProperty(value = "로그인 상태의 사용자의 정보")
    private User user;

    @NotNull
    private String content;

    public DiscussionBoardReply toEntity(DiscussionReplyInsertRequest requestDto){
        return DiscussionBoardReply.builder()
                .discussionBoard(requestDto.discussionBoard)
                .user(requestDto.user)
                .content(requestDto.content)
                .build();
    }

}
