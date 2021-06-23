package udodog.goGetterServer.controller.api.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.service.user.UserService;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(tags = {"회원 관련 API"})
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "이메일 전송 API",notes = "회원가입 이메일 인증시 사용되는 API 입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 전송성공 \t\n 2. 이메일중복")
    })
    @GetMapping("/email-confirm")
    public ResponseEntity<EntityModel<DefaultRes>> emailConfirm(
            HttpServletRequest request,
            @RequestParam("email") String email
    ){
        return new ResponseEntity<>(EntityModel.of(userService.emailConfirm(request, email),
                linkTo(methodOn(UserController.class).emailConfirm(null,null)).withSelfRel(),
                linkTo(methodOn(UserController.class).issuanceConfirm(null,null)).withRel("issuance-confirm"))
                , HttpStatus.OK);
    }

    @ApiOperation(value = "발급 번호 확인 API",notes = "이메일 발급번호를 체크하는 API 입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 일치\t\n 2. 불일치")
    })
    @GetMapping("/issuance-confirm")
    public ResponseEntity<EntityModel<DefaultRes>> issuanceConfirm(
            HttpServletRequest request,
            @RequestParam("number") String number
    ){
        return new ResponseEntity<>(EntityModel.of(userService.issuanceConfirm(request,number),
                linkTo(methodOn(UserController.class).issuanceConfirm(null, null)).withSelfRel(),
                linkTo(methodOn(UserController.class).singUp()).withRel("sing-up"))
                ,HttpStatus.OK);
    }

    @ApiOperation(value = "회원 가입 API",notes = "회원 가입 API 입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 성공 \t\n 2. 실패")
    })
    @PostMapping("/signup")
    public ResponseEntity<EntityModel<DefaultRes>> singUp(){
        return null;
    }

}
