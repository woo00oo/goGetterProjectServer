package udodog.goGetterServer.controller.api.oauth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import udodog.goGetterServer.model.enumclass.SocialLoginType;
import udodog.goGetterServer.service.social.OauthService;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class OauthController {
    private final OauthService oauthService;

    /**
     * 사용자로부터 SNS 로그인 요청을 Social Login Type을 받아 처리
     * @param socialLoginType (GOOGLE, FACEBOOK)
     */

    @GetMapping ("/{socialLoginType}")
    public void socialLoginType (@PathVariable("socialLoginType")SocialLoginType socialLoginType){
        log.info(">> 사용자로부터 SNS 로그인 요청을 받음 :: {} Social Login", socialLoginType);
        oauthService.request(socialLoginType);
    }

    /**
     * Social Login API Sever 요청에 의한 callback을 처리
     * @param socialLoginType (GOOGLE, FACEBOOK)
     * @param code API Server 로 부터 넘어오는 code
     * @return SNS Login 요청 결과로 받은 Json 형태의 String 문자열(access_token, refresh_token 등)
     */
    @GetMapping("/{socialLoginType}/callback")
    public String callback(@PathVariable("socialLoginType") SocialLoginType socialLoginType,
        @RequestParam("code") String code) {
        log.info(">> 소셜 로그인 API 서버로부터 받은 code :: {}", code);
        return oauthService.requestAccessToken(socialLoginType, code);
    }
}
