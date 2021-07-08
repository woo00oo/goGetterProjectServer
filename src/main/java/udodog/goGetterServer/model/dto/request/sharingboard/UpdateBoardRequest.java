package udodog.goGetterServer.model.dto.request.sharingboard;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;


@Getter
@NoArgsConstructor
public class UpdateBoardRequest {

    @NotEmpty
    private Long userId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @NotEmpty
    private String bookTitle;

    private String sharingBoardTag;

}
