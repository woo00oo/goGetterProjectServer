package udodog.goGetterServer.model.dto.request.sharingboard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateBoardRequest {


    @NotEmpty
    private Long userId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

}
