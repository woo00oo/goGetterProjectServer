package udodog.goGetterServer.model.dto.request.sharingboard;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotEmpty;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBoardRequest {

    @NotEmpty
    @ApiModelProperty(value = "현재 로그인중인 사용자의 userId")
    private Long userId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @NotEmpty
    private String bookTitle;

    private List<String> sharingBoardTagList;


    public CreateBoardRequest(@NotEmpty Long userId, @NotEmpty String title, @NotEmpty String content, @NotEmpty String bookTitle) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.bookTitle = bookTitle;
    }
}
