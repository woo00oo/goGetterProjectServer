package udodog.goGetterServer.controller.api.bookreport;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import udodog.goGetterServer.model.converter.bookreport.BookReportConverter;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.bookreport.BookreportResponseDto;
import udodog.goGetterServer.service.bookreport.BookreportService;

@Api(tags = {"독서 기록 관련 API"})
@RestController
@RequiredArgsConstructor
public class BookReportController {

    private final BookReportConverter bookReportConverter;
    private final BookreportService bookreportService;

    @ApiOperation( value = "독서 기록 전체 목록 조회 API", notes = "독서 기록의 전체 목록 조회 API" )
    @ApiResponses( value = {
            @ApiResponse( code=200, message = "1. 조회성공 \t\n 2. 데이터없음 \t\n 3. 토큰에러" )
    })

    // 전체 조회 관련 Method
    @GetMapping("/api/book-reports")
    public ResponseEntity<EntityModel<DefaultRes<Page<BookreportResponseDto>>>> totalBookReportFindAll(
            @PageableDefault( sort = "id", direction = Sort.Direction.DESC, size = 10 ) Pageable pageable ) {  // Index Value를 이용 최신 날짜순을 기준으로 내림차순으로 페이지당 10개씩 출력

        return new ResponseEntity<>(bookReportConverter.toModel( bookreportService.searchBookReportList(pageable)), HttpStatus.OK );
    } // totalBookReportFindAll() 끝

} // Class 끝
