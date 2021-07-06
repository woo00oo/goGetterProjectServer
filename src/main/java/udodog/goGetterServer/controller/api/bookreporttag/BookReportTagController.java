package udodog.goGetterServer.controller.api.bookreporttag;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udodog.goGetterServer.model.converter.bookreporttag.BookReportTagConverter;
import udodog.goGetterServer.model.converter.bookreporttag.BookReportTagPagingConverter;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.bookreporttag.BookReportTagInsertRequestDto;
import udodog.goGetterServer.model.dto.request.bookreporttag.BookReportTagUpdateRequestDto;
import udodog.goGetterServer.model.dto.response.bookreporttag.BookReportTagResponseDto;
import udodog.goGetterServer.model.entity.BookReportTag;
import udodog.goGetterServer.service.bookreporttag.BookReportTagService;

import javax.validation.Valid;
import java.util.List;

@Api( tags = { "독서 기록 Tag 관련 API" })
@RestController
@RequiredArgsConstructor
public class BookReportTagController {

    private final BookReportTagPagingConverter bookReportTagPagingConverter;
    private final BookReportTagConverter bookReportTagConverter;
    private final BookReportTagService bookReportTagService;

    @ApiOperation(value = "독서기록 Tag 등록 API",notes = "독서 기록 Tag 등록 API")
    @ApiResponses(value = { @ApiResponse(code=200, message = "1. 등록성공 \t\n 2. 등록실패 \t\n 3. 토큰에러")})

    // Tag 등록 Method
    @PostMapping("/api/book-reports/tag")
    public ResponseEntity<EntityModel<DefaultRes<BookReportTagResponseDto>>> insertTag (
            @ApiParam("tagName을 제외한 모든 사항 필수")
            @RequestBody BookReportTagInsertRequestDto bookReportTagInsertRequestDto,
            @RequestParam("bookReportId") Long bookReportId) {

        return new ResponseEntity<>(bookReportTagConverter.toModel(bookReportTagService.insertTag(bookReportTagInsertRequestDto, bookReportId)), HttpStatus.OK);
    } // insertTag() 끝

    // Tag 조회 Method
    @GetMapping("/api/book-reports/tag")
    public ResponseEntity<EntityModel<DefaultRes<Page<BookReportTagResponseDto>>>> seartchBookReportTagPaging (
            @PageableDefault( sort = "bookReportTagId", direction = Sort.Direction.DESC, size = 10 )Pageable pageable   // 한 페이지 내 등록 가능 Tag 10개 모두 조회
            ){
        return new ResponseEntity<>(bookReportTagPagingConverter.toModel(bookReportTagService.getBookTagPaging(pageable)), HttpStatus.OK);
    } // seartchBookReportTagPaging() 끝

    // Tag 수정 Method
    @PatchMapping("/api/book-reports/tag/{bookReportTagId}")
    public ResponseEntity<EntityModel<DefaultRes<BookReportTagUpdateRequestDto>>> bookReportTagUpdate(
            @PathVariable("bookReportTagId") Long bookReportTagId,
            @RequestParam("bookReportId") Long bookReportId,
            @Valid @RequestBody BookReportTagUpdateRequestDto bookReportTagUpdateRequestDto
    ) {
        return new ResponseEntity<>(bookReportTagConverter.toModel(bookReportTagService.updateTag(bookReportTagUpdateRequestDto, bookReportTagId, bookReportId)), HttpStatus.OK);

    } // bookReportTagUpdate() 끝

    // Tag 삭제 Method
    @DeleteMapping("/api/book-reports/tag/{bookReportTagId}")
    public ResponseEntity<EntityModel<DefaultRes<BookReportTag>>> bookReportTagDelete (
            @PathVariable("bookReportTagId") Long bookReportTagId,
            @RequestParam("bookReportId") Long bookReportId
    ) {
        return new ResponseEntity<>(bookReportTagConverter.toModel(bookReportTagService.deleteTag(bookReportTagId, bookReportId)), HttpStatus.OK);
    } // bookReportTagDelete() 끝


} // Class 끝
