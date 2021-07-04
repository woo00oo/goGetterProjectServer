package udodog.goGetterServer.service.bookreport;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.bookreport.BookreportResponseDto;
import udodog.goGetterServer.repository.BookReportRepository;
import udodog.goGetterServer.repository.UserRepository;
import udodog.goGetterServer.repository.querydsl.BookReportQueryRepository;

@Service
@RequiredArgsConstructor
public class BookreportService {

    private final UserRepository userRepository;
    private final BookReportRepository bookReportRepository;
    private final BookReportQueryRepository bookReportQueryRepository;

    // 전체 조회
    public DefaultRes<Page<BookreportResponseDto>> searchBookReportList(Pageable pageable) { // 페이징 처리

        Page<BookreportResponseDto> bookReportPage = bookReportQueryRepository.findAllWithFetchJoin(pageable);

        if (bookReportPage.getTotalElements() == 0) { // bookreport의 내용이 없다면?
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");

        } else {                                      // bookreport의 내용이 있다면?
            return DefaultRes.response(HttpStatus.OK.value(), "조회성공", bookReportPage);
        } // if-else 끝

    } // searchBookReportList() 끝




} // Class 끝
