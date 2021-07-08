package udodog.goGetterServer.model.dto.response.bookreport;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BookreportResponseDto {

    private Long userId;
    private Long bookReportId;                      // 독서 기록 Index 번호
    private String userNickname;                    // User Nickname
    private String bookName;                        // 책 제목
    private String title;                           // 독서 기록 글 제목
    private LocalDateTime createAt;                     // 작성일
    private String bookReportTag;                    // 태그 내용

    public BookreportResponseDto(Long userId, Long bookReportId, String userNickname, String bookName, String title, LocalDateTime createAt, String bookReportTag) {
        this.userId = userId;
        this.bookReportId = bookReportId;
        this.userNickname = userNickname;
        this.bookName = bookName;
        this.title = title;
        this.createAt = createAt;
        this.bookReportTag = bookReportTag;
    }// 생성자 끝

} // Class 끝
