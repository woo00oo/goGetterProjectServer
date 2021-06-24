package udodog.goGetterServer.controller.api.user;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udodog.goGetterServer.model.converter.user.UserConverter;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.UserSignUpRequestDto;
import udodog.goGetterServer.service.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Api(tags = {"회원 관련 API"})
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserConverter userConverter;

    @ApiOperation(value = "이메일 전송 API",notes = "회원가입 이메일 인증시 사용되는 API 입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 전송성공 \t\n 2. 이메일중복")
    })
    @GetMapping("/email-confirm")
    public ResponseEntity<EntityModel<DefaultRes>> emailConfirm(
            HttpServletRequest request,
            @RequestParam("email") String email
    ){
        return new ResponseEntity<>(userConverter.toModel(userService.emailConfirm(request, email)),HttpStatus.OK);
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
        return new ResponseEntity<>(userConverter.toModel(userService.issuanceConfirm(request,number)),HttpStatus.OK);
    }

    @ApiOperation(value = "회원 가입 API",notes = "회원 가입 API 입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1.성공 \t\n 2.이메일중복")
    })
    @PostMapping("/signup")
    public ResponseEntity<EntityModel<DefaultRes>> signUp(
            @ApiParam(value = "필수 : 모든 항목" +
                    " \t\n 비밀번호는 인코딩 적용해서 요청 ")
            @Valid @RequestBody UserSignUpRequestDto requestDto
            ){
        return new ResponseEntity<>(userConverter.toModel(userService.signUp(requestDto)), HttpStatus.OK);
    }

    @ApiOperation(value = "로그인 API",notes = "로그인 API 입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1.성공 \t\n 2.아이디불일치 \t\n 3.이메일불일치")
    })
    @PostMapping("/signin")
    public ResponseEntity<EntityModel<DefaultRes>> signin(){
        return null;
    }

}
