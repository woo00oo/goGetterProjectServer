package udodog.goGetterServer.model.dto.request.sharingboard;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import udodog.goGetterServer.model.entity.User;

import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class creatBoardRequest {

    @NotEmpty
    @ApiModelProperty(value = "현재 로그인중인 사용자의 userId")
    private Long userId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;
}
