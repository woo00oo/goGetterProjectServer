package udodog.goGetterServer.model.dto.response.bookreport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class BookreportResponseDto {

    private Long bookReportId;       // 독서 기록 Index 번호
    private String userNickname;     // User Nickname
    private String bookName;        // 책 제목
    private String title;           // 독서 기록 글 제목
    private LocalDate createAt;     // 작성일

    public BookreportResponseDto ( Long bookReportId, String userNickname, String bookName, String title, LocalDate createAt ) {

        this.bookReportId = bookReportId;
        this.userNickname = userNickname;
        this.bookName = bookName;
        this.title = title;
        this.createAt = createAt;

    } // 생성자 끝

} // Class 끝
