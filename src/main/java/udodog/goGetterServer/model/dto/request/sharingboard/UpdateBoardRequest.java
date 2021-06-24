package udodog.goGetterServer.model.dto.request.sharingboard;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import udodog.goGetterServer.model.entity.User;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class creatBoardRequest {

    private User user;

    @NotNull
    private String title;

    @NotNull
    private String content;
}
