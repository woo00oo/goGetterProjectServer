package udodog.goGetterServer.controller.api.blackmember;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import udodog.goGetterServer.model.converter.blackmember.BlackMemberEntityToModelConvertor;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.BlackMemberManagementResDto;
import udodog.goGetterServer.model.entity.BlackMemberManagement;
import udodog.goGetterServer.service.blackmember.BlackMemberManagementService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = {"Black 회원 관리 API"})
@RestController
@RequiredArgsConstructor
public class BlackMemberManagementController {

    private final BlackMemberEntityToModelConvertor blackMemberEntityToModelConvertor;
    private final BlackMemberManagementService blackMemberManagementService;

//    @ApiOperation(value = "블랙 회원 등록 API", notes = "블랙회원 등록 시 사용되는 API")
//    @ApiResponses(value ={@ApiResponse(code = 200, message = "1. 등록성공 \t\n 2. 등록실패")})
//
//    @PostMapping("/admapi/blackmembers")    // Http Method 및 URI 설정
//    public ResponseEntity<EntityModel<DefaultRes<BlackMemberManagement>>> insertBlackMember(HttpServletRequest request, @RequestParam("userId") Long userId) { // Black 회원 등록
//        BlackMemberManagement bmm = new BlackMemberManagement();
//
//        return ResponseEntity.ok(blackMemberEntityToModelConvertor.toModel(
//                DefaultRes.response(HttpStatus.OK.value(), "Black List 회원이 추가되었습니다!", bmm)
//        ));

//    } // insertBlackMember() 끝
    @ApiOperation(value = "블랙 회원 전체 조회 기능", notes = "Black Member 전체조회 기능 및 페이징 처리)")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "1.조회 성공 \t\n 2.토큰 에러 \t\n 3.데이터 없음")})

    @GetMapping("/admapi/blackmembers")
    public ResponseEntity<EntityModel<DefaultRes<List<BlackMemberManagementResDto>>>> searchBlackMember(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 15)Pageable pageable) { // Black 회원 조회 페이징 처리

        //        BlackMemberManagement bmm = new BlackMemberManagement();

        return new ResponseEntity<>(blackMemberEntityToModelConvertor
                                          .toModel(blackMemberManagementService.findAllBlackMember(pageable)), HttpStatus.OK);
    }  // searchBlackMember() 끝




} // Class 끝
