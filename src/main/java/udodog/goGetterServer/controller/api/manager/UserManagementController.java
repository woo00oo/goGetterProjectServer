package udodog.goGetterServer.controller.api.manager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udodog.goGetterServer.model.converter.manager.ManagerPagingConverter;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.manager.search.UserSearchResponseDto;
import udodog.goGetterServer.service.manager.UserSearchService;

@Api(tags = {"관리자 회원 관리 API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class UserManagementController {

    private final UserSearchService userSearchService;
//    private final ManagerConverter managerConverter;
    private final ManagerPagingConverter managerPagingConverter;

    @ApiOperation(value = "회원 전체 조회 API", notes = "관리자 Page 회원 전체 목록 조회 API")
    @ApiResponses(value = { @ApiResponse(code=200, message = "1. 조회 성공 \t\n 2. 데이터 없음 \t\n 3.Token Error")})

    // 전체 조회 관련 Method
    @GetMapping("user/list")
    public ResponseEntity<EntityModel<DefaultRes<Page<UserSearchResponseDto>>>> totalMemberFindAll (@PageableDefault(size = 20)Pageable pageable) { //  페이지당 20개씩 출력

        return new ResponseEntity<>(managerPagingConverter.toModel( userSearchService.searchUserList( pageable )), HttpStatus.OK);
    } // totalMemberFindAll() 끝

    @ApiOperation(value = "Black 회원 전체 조회 API", notes = "관리자 Page Black 회원 전체 목록 조회 API")
    @ApiResponses(value = { @ApiResponse(code=200, message = "1. 조회 성공 \t\n 2. 데이터 없음 \t\n 3.Token Error")})

    // Black User 전체 조회 관련 Method
    @GetMapping("bkuser/list")
    public ResponseEntity<EntityModel<DefaultRes<Page<UserSearchResponseDto>>>> totalBKMemberFindAll (@PageableDefault(size = 20)Pageable pageable) { //  페이지당 20개씩 출력

        return new ResponseEntity<>(managerPagingConverter.toModel( userSearchService.searchBKUserList( pageable )), HttpStatus.OK);
    } // totalBKMemberFindAll() 끝
} // Class 끝
