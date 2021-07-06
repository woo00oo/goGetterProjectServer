package udodog.goGetterServer.model.dto.request.bookreport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import udodog.goGetterServer.model.entity.BookReport;
import udodog.goGetterServer.model.entity.User;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BookreportInsertRequestDto {

//    @NotNull private Long userId;                        // 글쓴이 Id 정보
    @NotEmpty private String bookName;                   // 책 제목
    @NotEmpty private String title;                      // 글 제목
    @NotEmpty private String content;                    // 글 내용
//    @FutureOrPresent private LocalDate createAt;         // 작성 날짜

    @Builder
    public BookReport toEntity(BookreportInsertRequestDto insetRequestDto, Optional<User> user) {

        return BookReport.builder()
                .user(user.get())
                .bookName(insetRequestDto.bookName)
                .title(insetRequestDto.title)
                .content(insetRequestDto.content)
                .build();

    } // toEntity() 끝

} // Class 끝
