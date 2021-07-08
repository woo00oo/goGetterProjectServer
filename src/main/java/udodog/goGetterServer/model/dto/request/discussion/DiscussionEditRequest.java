package udodog.goGetterServer.model.dto.request.discussion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DiscussionEditRequest {

    @NotNull
    private String title;                           // 게시판 제목

    @NotNull
    private String content;                         // 게시판 내용

    LocalDate createAt = LocalDate.now();           // 게시글 수정일

}
