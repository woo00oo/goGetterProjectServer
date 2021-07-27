package udodog.goGetterServer.service.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.enumclass.oauth.SocialLoginType;
import udodog.goGetterServer.service.oauth.social.FacebookOauth;
import udodog.goGetterServer.service.oauth.social.GoogleOauth;
import udodog.goGetterServer.service.oauth.social.SocialOauth;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OauthService {
    private final List<SocialOauth> socialOauthList;
    private final HttpServletResponse response;
    private final GoogleOauth googleOauth;
    private final FacebookOauth facebookOauth;

    public void request(SocialLoginType socialLoginType) {
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
        String redirectURL = socialOauth.getOauthRedirectURL();
        try {
            response.sendRedirect(redirectURL);
        } catch (IOException e) {
            new IllegalArgumentException("알 수 없는 구글 로그인 Access Token 요청 URL 입니다");
        }
    }

    public DefaultRes requestAccessToken(SocialLoginType socialLoginType, String code) {
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);

        if(socialLoginType == SocialLoginType.GOOGLE){
            String token = socialOauth.requestAccessToken(code);
            googleOauth.requestAccessTokenUsingURL(token);
        }else if (socialLoginType == SocialLoginType.FACEBOOK){
            String token = socialOauth.requestAccessToken(code);
            facebookOauth.requestAccessTokenUsingURL(token);
        }

        return DefaultRes.response(HttpStatus.OK.value(), "등록실패");
    }

    private SocialOauth findSocialOauthByType(SocialLoginType socialLoginType) {
        return socialOauthList.stream()
                .filter(x -> x.type() == socialLoginType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("알 수 없는 SocialLoginType 입니다."));
    }
}