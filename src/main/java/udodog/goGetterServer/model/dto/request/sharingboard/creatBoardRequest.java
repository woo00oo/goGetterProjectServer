package udodog.goGetterServer.model.dto.request.sharingboard;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.validation.constraints.NotEmpty;


@Getter
@NoArgsConstructor
public class creatBoardRequest {

    @NotEmpty
    @ApiModelProperty(value = "현재 로그인중인 사용자의 userId")
    private Long userId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;
}
