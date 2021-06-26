package udodog.goGetterServer.model.dto.response.sharingboard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WriterInfo {

    private String nickName;

    private String profileUrl;

}
