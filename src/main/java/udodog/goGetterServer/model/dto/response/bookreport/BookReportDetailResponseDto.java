package udodog.goGetterServer.model.dto.response.bookreport;

import lombok.Getter;
import udodog.goGetterServer.model.entity.BookReport;
import udodog.goGetterServer.model.entity.BookReportTag;

import java.time.LocalDate;
import java.util.List;

@Getter
public class BookReportDetailResponseDto {

    private Long bookReportId;
    private String bookName;
    private String title;
    private String content;
    private LocalDate createAt;
    private String bookReportTag;

    public BookReportDetailResponseDto (BookReport bookReport, BookReportTag bookReportTag) {
        this.bookReportId = bookReport.getBookReportId();
        this.bookName = bookReport.getBookName();
        this.title = bookReport.getTitle();
        this.content = bookReport.getContent();
        this.createAt = bookReport.getCreatedAt();
        this.bookReportTag = bookReportTag.getTagName();
    } // 생성자 끝

} // Class 끝
