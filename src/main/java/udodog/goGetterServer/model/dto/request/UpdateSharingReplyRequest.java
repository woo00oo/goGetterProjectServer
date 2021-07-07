package udodog.goGetterServer.model.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
public class UpdateSharingReplyRequest {
    @NotEmpty
    @ApiModelProperty(value = "현재 로그인중인 사용자의 userId")
    private Long userId;

    @NotEmpty
    private String comment;

    @NotEmpty
    private Long replyId;

}
