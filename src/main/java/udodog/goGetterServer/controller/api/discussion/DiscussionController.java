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

    @GetMapping("/api/discussions") // 전체 조회 Controller
    public ResponseEntity<EntityModel<DefaultRes<List<DiscussionReseponseDto>>>> getBoardList(
            @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC, size = 7) Pageable pageable // 최신 날짜순으로 내림차순, 페이지당 7개씩 출력
    ){
        return new ResponseEntity<>(discussionListConvertor.toModel(discussionService.getBoardList(pageable)), HttpStatus.OK);
    }

    @ApiOperation(value = "토론게시판 상세 페이지 API",notes = "상세 페이지 API입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 상세보기성공 \t\n 2. 데이터없음 \t\n 3. 토큰에러")
    })

    @GetMapping("/api/user/discussions") // 상세보기 Controller
    public ResponseEntity<EntityModel<DefaultRes<DiscussionDetailResponse>>> getDetailBoard(@RequestParam("id") Long id){
        return new ResponseEntity<>(discussionConvertor.toModel(discussionService.getDetailBoard(id)), HttpStatus.OK);
    }

    @ApiOperation(value = "토론게시판 글쓰기 API",notes = "글쓰기 API입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1.등록성공 \\t\\n 2.등록실패 \\t\\n 3. 토큰에러")
    })

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

    @PutMapping("/api/user/discussions")
    public ResponseEntity<EntityModel<DefaultRes>> updateBoard(
            @RequestBody DiscussionEditRequest updatetRequestDto, @PathVariable Long id){
        return new ResponseEntity<>(discussionConvertor.toModel(discussionService.updateBoard(updatetRequestDto, id)), HttpStatus.OK);
    }

    @ApiOperation(value = "토론게시판 글삭제 API",notes = "글삭제 API입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1.삭제성공 \\t\\n 2. 삭제실패 \\t\\n 3. 토큰에러")
    })
    @DeleteMapping("/api/user/discussions")
    public ResponseEntity<EntityModel<DefaultRes<DiscussionDetailResponse>>> deleteBoard (@PathVariable Long id){
        return new ResponseEntity<>(discussionConvertor.toModel(discussionService.delete(id)), HttpStatus.OK);
    }
}
