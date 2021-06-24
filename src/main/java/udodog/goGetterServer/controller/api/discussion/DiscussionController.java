package udodog.goGetterServer.controller.api.discussion;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import udodog.goGetterServer.model.converter.discussion.DiscussionConvertor;
import udodog.goGetterServer.model.converter.discussion.DiscussionListConvertor;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.discussion.DiscussionReseponseDto;
import udodog.goGetterServer.service.discussion.DiscussionService;

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

    @GetMapping("/discussions") // 전체 조회 Controller
    public ResponseEntity<EntityModel<DefaultRes<List<DiscussionReseponseDto>>>> getBoardList(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 7) Pageable pageable
    ){
        return new ResponseEntity<>(discussionListConvertor.toModel(discussionService.getBoardList(pageable)), HttpStatus.OK);
    }

}
