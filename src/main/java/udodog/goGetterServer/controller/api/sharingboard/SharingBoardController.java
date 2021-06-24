package udodog.goGetterServer.controller.api.sharingboard;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udodog.goGetterServer.model.converter.sharingboard.SharingListConvertor;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.sharingboard.UpdateBoardRequest;
import udodog.goGetterServer.model.dto.request.sharingboard.creatBoardRequest;
import udodog.goGetterServer.model.dto.response.sharingboard.BoardResponse;
import udodog.goGetterServer.model.dto.response.sharingboard.SimpleBoardResponse;
import udodog.goGetterServer.service.sharingboard.SharingBoardService;
import java.util.List;

@Api(tags = {"공유 게시판 API"})
@RestController
@RequiredArgsConstructor
public class SharingBoardController {

    private final SharingBoardService sharingBoardService;
    private final SharingListConvertor sharingListConvertor;

    @ApiOperation(value = "공유 게시판 전체 조회 API", notes = "공유 게시판 게시글 목록 조회 시 사용되는 API입니다.")
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "1.조회 성공 /t/n 2.데이터 없음 /t/n 3.토큰 에러"),
    })
    @GetMapping("/api/sharings")
    public ResponseEntity<EntityModel<DefaultRes<List<SimpleBoardResponse>>>> getBoardList(
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC, size = 9) Pageable pageable){
        return new ResponseEntity<>(sharingListConvertor.toModel(sharingBoardService.getBoardList(pageable)), HttpStatus.OK);
    }

//    @ApiOperation(value = "공유 게시판 상세 조회 API", notes = "공유 게시판 게시글 상세 조회 시 사용되는 API입니다.")
//    @ApiResponses(value ={
//            @ApiResponse(code = 200, message = "조회 성공"),
//            @ApiResponse(code = 204, message = "데이터 없음")
//    })
//    @GetMapping("/api/sharings")
//    public ResponseEntity<DefaultRes<BoardResponse>> getBoardDetail(@RequestParam("id") Long boardId){
//        return new ResponseEntity<>(sharingBoardService.getBoardDetail(boardId), HttpStatus.OK);
//    }
//
//
//
//    @ApiOperation(value = "공유 게시판 글 작성 조회 API", notes = "공유 게시판 게시글 작성 시 사용되는 API입니다.")
//    @ApiResponses(value ={
//            @ApiResponse(code = 201, message = "글 등록 성공"),
//            @ApiResponse(code = 204, message = "글 등록 실패")
//    })
//    @PostMapping("/userapi/sharings")
//    public ResponseEntity<DefaultRes> createSharingBoard(@RequestBody creatBoardRequest request){
//        return new ResponseEntity<>(sharingBoardService.createSharingBoard(request), HttpStatus.OK);
//    }
//
//    @ApiOperation(value = "공유 게시판 글 수정 API", notes = "공유 게시판 게시글 수정 시 사용되는 API입니다.")
//    @PatchMapping("/userapi/sharings")
//    @ApiResponses(value ={
//            @ApiResponse(code = 200, message = "1. 글 수정 성공 \n\t 2. 글 수정 실패"),
//            @ApiResponse(code = 204, message = "글이 존재하지 않음")
//    })
//    public ResponseEntity<DefaultRes> updateSharingBoard(@RequestParam("id") Long boardId, @RequestBody UpdateBoardRequest request){
//        return new ResponseEntity<>(sharingBoardService.updateSharingBoard(boardId,request), HttpStatus.OK);
//    }
//
//    @ApiOperation(value = "공유 게시판 글 삭제 API", notes = "공유 게시판 게시글 삭제 시 사용되는 API입니다.")
//    @ApiResponses(value ={
//            @ApiResponse(code = 200, message = "1. 글 삭제 성공 \n\t 2. 글 삭제 실패"),
//            @ApiResponse(code = 204, message = "글이 존재하지 않음")
//    })
//    @DeleteMapping("/userapi/sharings")
//    public ResponseEntity<DefaultRes> deleteSharingBoard(@RequestParam("id") Long boardId){
//        return new ResponseEntity<>(sharingBoardService.deleteSharingBoard(boardId), HttpStatus.OK);
//    }

}