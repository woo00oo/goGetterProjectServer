package udodog.goGetterServer.model.dto.response.bookreport;

import lombok.Getter;
import lombok.NoArgsConstructor;
import udodog.goGetterServer.model.entity.BookReport;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BookReportDetailResponseDto {

    private String bookName;
    private String title;
    private String content;
    private LocalDateTime createAt;
    private String bookReportTag;

    public BookReportDetailResponseDto(BookReport bookReport) {
        this.bookName = bookReport.getBookName();
        this.title = bookReport.getTitle();
        this.content = bookReport.getContent();
        this.createAt = bookReport.getCreatedAt();
        this.bookReportTag = bookReport.getBookName();
    }
} // Class 끝
