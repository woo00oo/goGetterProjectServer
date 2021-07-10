package udodog.goGetterServer.controller.api.token;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.service.user.SessionService;

import javax.servlet.http.HttpServletRequest;

@Api(tags = {"엑세스 토큰 재발급 API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReplaceTokenController {

    private final SessionService sessionService;

    @ApiOperation(value = "엑세스 토큰 재발급 API",notes = "리프레시 토큰이 유효하다면 엑세스 토큰을 재발급 합니다.(유효하지 않다면 로그아웃 처리")
    @GetMapping("/token")
    public ResponseEntity<DefaultRes> replaceToken(
            HttpServletRequest request
    ){
        DefaultRes defaultRes = sessionService.replaceToken(request);

        if (defaultRes.getStatusCode() == HttpStatus.UNAUTHORIZED.value()){
            return new ResponseEntity<>(defaultRes, HttpStatus.UNAUTHORIZED);
        }else{
            return new ResponseEntity<>(defaultRes, HttpStatus.OK);
        }

    }
}
