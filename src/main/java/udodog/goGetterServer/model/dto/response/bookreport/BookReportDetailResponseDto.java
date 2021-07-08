package udodog.goGetterServer.model.dto.response.bookreport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookReportDetailResponseDto {

    private Long bookReportId;
    private String userNickName;
    private String bookName;
    private String title;
    private String content;
    private LocalDateTime createAt;
    private String bookReportTag;


    public BookReportDetailResponseDto (Optional<BookReportDetailResponseDto> bookReportDetailResponseDto) {
        this.bookReportId = bookReportDetailResponseDto.get().getBookReportId();
        this.userNickName = bookReportDetailResponseDto.get().getUserNickName();
        this.bookName = bookReportDetailResponseDto.get().getBookName();
        this.title = bookReportDetailResponseDto.get().getTitle();
        this.content = bookReportDetailResponseDto.get().getContent();
        this.createAt = bookReportDetailResponseDto.get().getCreateAt();
        this.bookReportTag = bookReportDetailResponseDto.get().getBookReportTag();
    } // 생성자 끝

} // Class 끝
