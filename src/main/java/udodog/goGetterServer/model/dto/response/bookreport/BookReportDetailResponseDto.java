package udodog.goGetterServer.model.dto.response.bookreport;

import lombok.Getter;
import udodog.goGetterServer.model.entity.BookReport;

import java.time.LocalDate;

@Getter
public class BookReportDetailResponseDto {

    private Long bookReportId;
    private String bookName;
    private String title;
    private String content;
    private LocalDate createAt;

    public BookReportDetailResponseDto (BookReport bookReport) {
        this.bookReportId = bookReport.getBookReportId();
        this.bookName = bookReport.getBookName();
        this.title = bookReport.getTitle();
        this.content = bookReport.getContent();
        this.createAt = bookReport.getCreatedAt();
    } // 생성자 끝

} // Class 끝
