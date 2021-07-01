package udodog.goGetterServer.controller.api.discussion;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udodog.goGetterServer.model.converter.discussion.DiscussionConvertor;
import udodog.goGetterServer.model.converter.discussion.DiscussionListConvertor;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.discussion.DiscussionEditRequest;
import udodog.goGetterServer.model.dto.request.discussion.DiscussionInsertRequestDto;
import udodog.goGetterServer.model.dto.response.discussion.DiscussionDetailResponse;
import udodog.goGetterServer.model.dto.response.discussion.DiscussionReseponseDto;
import udodog.goGetterServer.service.discussion.DiscussionService;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {"토론 게시판 관련 API"})
@RestController
@RequiredArgsConstructor
public class DiscussionController {

    private final DiscussionListConvertor discussionListConvertor;
    private final DiscussionConvertor discussionConvertor;
    private final DiscussionService discussionService;

    @ApiOperation(value = "토론게시판 전체 목록 조회 API",notes = "전체 목록 조회 API입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 조회성공 \t\n 2. 데이터없음 \t\n 3. 토큰에러")
    })

    // 전체 조회 Controller
    @GetMapping("/api/discussions")
    public ResponseEntity<EntityModel<DefaultRes<List<DiscussionReseponseDto>>>> getBoardList(
            @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC, size = 7) Pageable pageable // 최신 날짜순으로 내림차순, 페이지당 7개씩 출력
    ){
        return new ResponseEntity<>(discussionListConvertor.toModel(discussionService.getBoardList(pageable)), HttpStatus.OK);
    }

    @ApiOperation(value = "토론게시판 상세 페이지 API",notes = "상세 페이지 API입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 상세보기성공 \t\n 2. 데이터없음 \t\n 3. 토큰에러")
    })

    // 상세보기 Controller
    @GetMapping("/api/bkuser/discussions")
    public ResponseEntity<EntityModel<DefaultRes<DiscussionDetailResponse>>> getDetailBoard(@RequestParam("id") Long id){
        return new ResponseEntity<>(discussionConvertor.toModel(discussionService.getDetailBoard(id)), HttpStatus.OK);
    }

    @ApiOperation(value = "토론게시판 글쓰기 API",notes = "글쓰기 API입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1.등록성공 \\t\\n 2.등록실패 \\t\\n 3. 토큰에러")
    })

    // 글등록 Controller
    @PostMapping("/api/user/discussions")
    public ResponseEntity<EntityModel<DefaultRes<DiscussionInsertRequestDto>>> insertBoard(
            @ApiParam(value = "필수 : 모든 항목" )
            @Valid@RequestBody DiscussionInsertRequestDto requestDto
    ){
        return new ResponseEntity<>(discussionConvertor.toModel(discussionService.insertBoard(requestDto)), HttpStatus.OK);
    }

    @ApiOperation(value = "토론게시판 글수정 API",notes = "글수정 API입니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "1.수정성공 \\t\\n 2.수정실패 \\t\\n 3.데이터없음 \\t\\n 4.토큰에러")
    })

    // 글 업데이트 Controller
    @PutMapping("/api/user/discussions")
    public ResponseEntity<EntityModel<DefaultRes>> updateBoard(
            @Valid@RequestBody DiscussionEditRequest updatetRequestDto, @RequestParam("id") Long id){
        return new ResponseEntity<>(discussionConvertor.toModel(discussionService.updateBoard(updatetRequestDto, id)), HttpStatus.OK);
    }

    @ApiOperation(value = "토론게시판 글삭제 API",notes = "글삭제 API입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1.삭제성공 \\t\\n 2. 삭제실패 \\t\\n 3. 데이터없음 \\t\\n 4. 토큰에러")
    })

    // 글 삭제 Controller
    @DeleteMapping("/api/user/discussions")
    public ResponseEntity<EntityModel<DefaultRes<DiscussionDetailResponse>>> deleteBoard (@RequestParam("id") Long id, @RequestParam("userId") Long userId){
        return new ResponseEntity<>(discussionConvertor.toModel(discussionService.delete(id, userId)), HttpStatus.OK);
    }

    @ApiOperation(value = "토론게시판 제목 검색 API",notes = "제목 검색 API입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1.검색성공 \\t\\n 2. 데이터없음 \\t\\n 3. 토큰에러")
    })

    // 제목으로 검색
    @GetMapping("/api/discussions/search-title")
    public ResponseEntity<EntityModel<DefaultRes<List<DiscussionReseponseDto>>>> searchTitle (@RequestParam("title") String title,
                                                                            @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC, size = 7) Pageable pageable){
        return new ResponseEntity<>(discussionListConvertor.toModel(discussionService.searchTitle(title, pageable)), HttpStatus.OK);
    }

    @ApiOperation(value = "토론게시판 내용 검색 API",notes = "내용 검색 API입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1.검색성공 \\t\\n 22. 데이터없음 \\t\\n 3. 토큰에러")
    })

    // 내용으로 검색
    @GetMapping("/api/discussions/search-content")
    public ResponseEntity<EntityModel<DefaultRes<List<DiscussionReseponseDto>>>> searchContent (@RequestParam("content") String content,
                                                                                              @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC, size = 7) Pageable pageable){
        return new ResponseEntity<>(discussionListConvertor.toModel(discussionService.searchContent(content, pageable)), HttpStatus.OK);
    }

    @ApiOperation(value = "토론게시판 제목+내용 검색 API",notes = "제목+내용 검색 API입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1.검색성공 \\t\\n 2. 데이터없음 \\t\\n 3. 토큰에러")
    })

    // 제목 + 내용으로 검색
    @GetMapping("/api/discussions/search-all")
    public ResponseEntity<EntityModel<DefaultRes<List<DiscussionReseponseDto>>>> searchAll (@RequestParam("search") String search,
                                                                                                @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC, size = 7) Pageable pageable){
        return new ResponseEntity<>(discussionListConvertor.toModel(discussionService.searchAll(search, pageable)), HttpStatus.OK);
    }
}
