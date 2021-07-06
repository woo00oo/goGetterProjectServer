package udodog.goGetterServer.service.bookreporttag;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.Pagination;
import udodog.goGetterServer.model.dto.request.bookreporttag.BookReportTagInsertRequestDto;
import udodog.goGetterServer.model.dto.request.bookreporttag.BookReportTagUpdateRequestDto;
import udodog.goGetterServer.model.dto.response.bookreporttag.BookReportTagResponseDto;
import udodog.goGetterServer.model.entity.BookReport;
import udodog.goGetterServer.model.entity.BookReportTag;
import udodog.goGetterServer.repository.BookReportTagRepository;
import udodog.goGetterServer.repository.querydsl.BookReportQueryRepository;
import udodog.goGetterServer.repository.querydsl.BookReportTagQueryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookReportTagService {

    private final BookReportQueryRepository bookReportQueryRepository;
    private final BookReportTagRepository bookReportTagRepository;
    private final BookReportTagQueryRepository bookReportTagQueryRepository;

    // Tag 입력
    public DefaultRes insertTag (BookReportTagInsertRequestDto bookReportTagInsertRequestDto, Long bookReportId) {

        Optional<BookReport> bookReport = bookReportTagQueryRepository.findById(bookReportId);

        if (bookReportTagInsertRequestDto == null) {  // Tag 입력 내용이 없다면?
            return DefaultRes.response(HttpStatus.OK.value(), "등록실패");
        } // if문 끝

        if (bookReport.get().getBookReportId().equals(bookReportId)) {
            bookReportTagRepository.save(bookReportTagInsertRequestDto.toEntiry(bookReport, bookReportTagInsertRequestDto));
        } // if문 끝

        return DefaultRes.response(HttpStatus.OK.value(), "등록성공");

    } // insertTag() 끝

    // tag 조회
    public DefaultRes<Page<BookReportTagResponseDto>> getBookTagPaging(Pageable pageable) {

        Page<BookReportTagResponseDto> bookReportTagResponseDtos = bookReportTagQueryRepository.findAllWithFetchJoin(pageable);

        if ( bookReportTagResponseDtos.getTotalElements() == 0 ) {
            return DefaultRes.response(HttpStatus.OK.value(), "Tag없음");

        } else {
            return DefaultRes.response(HttpStatus.OK.value(), "Tag찾음",bookReportTagResponseDtos, new Pagination( bookReportTagResponseDtos ));
        } // if-else문 끝

    } // getBookTagPaging() 끝

    private List<BookReportTagResponseDto> tagData(Page<BookReportTagResponseDto> bookReportTagResponseDtos) {

        return bookReportTagResponseDtos.stream().map(BookReportTagResponseDto::new).collect(Collectors.toList());

    } // tagData() 끝

    // tag 수정
    public DefaultRes updateTag (BookReportTagUpdateRequestDto bookReportTagUpdateRequestDto, Long bookReportTagId, Long bookReportId) {

        Optional<BookReportTag> optionalBookReportTag = bookReportTagQueryRepository.findByBookReportTagId(bookReportTagId);

        if (optionalBookReportTag.isEmpty()) { // Tag가 비어있다면?
            return DefaultRes.response(HttpStatus.OK.value(), "Tag없음");
        } // if문 끝

        return optionalBookReportTag.filter(bookReportTag -> bookReportTag.getBookReportTagId().equals(bookReportTagId))
                .filter(bookReportTag -> bookReportTag.getBookReport().getBookReportId().equals(bookReportId))
                .map(bookReportTag -> {

                    bookReportTagQueryRepository.bookReportTagUpdate(bookReportTagUpdateRequestDto, bookReportTag.getBookReportTagId());

                    return DefaultRes.response(HttpStatus.OK.value(), "수정성공");

                }).orElseGet(()-> DefaultRes.response(HttpStatus.OK.value(), "수정실패"));

    } // updateTag() 끝

    public DefaultRes deleteTag(Long bookReportTagId, Long bookReportId) {
        Optional<BookReportTag> tagDelete = bookReportTagQueryRepository.findByBookReportTagId(bookReportTagId);

        if (tagDelete.isEmpty()) { // 삭제 대상이 없다면?
            return DefaultRes.response(HttpStatus.OK.value(), "삭제대상없음");
        } // if 문 끝

        return tagDelete.filter(bookReportTag -> bookReportTag.getBookReportTagId().equals(bookReportTagId))
                        .filter(bookReportTag -> bookReportTag.getBookReport().getBookReportId().equals(bookReportId)).map(bookReportTag -> {

                            bookReportTagQueryRepository.deleteByBookReportTagId(bookReportTag.getBookReportTagId(), bookReportTag.getBookReport().getBookReportId());

                            return DefaultRes.response(HttpStatus.OK.value(), "삭제성공");

                }).orElseGet(()-> DefaultRes.response(HttpStatus.OK.value(), "삭제실패"));

    } // deleteTag() 끝
} // Class 끝
