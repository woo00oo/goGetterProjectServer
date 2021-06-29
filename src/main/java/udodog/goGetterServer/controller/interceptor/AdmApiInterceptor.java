package udodog.goGetterServer.controller.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import udodog.goGetterServer.model.dto.response.user.JwtAccessTokenResponse;
import udodog.goGetterServer.model.enumclass.UserGrade;
import udodog.goGetterServer.model.utils.JwtUtil;
import udodog.goGetterServer.service.user.SessionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdmApiInterceptor implements HandlerInterceptor {

    @Autowired
    private SessionService sessionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("Authorization");

        if (token == null) {
            response.sendError(401,"요청에러");
            return false;
        }

        String jwtToken = token.substring("Bearer ".length());
        Claims claims = JwtUtil.getClaims(jwtToken);
        if (claims == null){
            response.sendError(401,"엑세스토큰 불일치");
            return false;
        }

        String tokenName = claims.get("token_name", String.class);
        String userGrade = claims.get("user_grade", String.class);

        if (userGrade.equals(UserGrade.BLACK.name()) || userGrade.equals(UserGrade.USER.name())){
            response.sendError(403,"권한없음");
            return false;
        }

        if(tokenName.equals(JwtUtil.ACCESS_TOKEN_NAME)){
            return true;
        }else if(tokenName.equals(JwtUtil.REFRESH_TOKEN_NAME)){
            Long userPk = claims.get("user_pk", Long.class);
            Boolean check = sessionService.refreshTokenCheck(userPk, jwtToken);
            if(check){
                String accessToken = JwtUtil.createAccessToken(userPk, UserGrade.ADMIN);
                accessTokenResponse(response, accessToken);
                return false;
            }else{
                response.sendError(401,"리프레시토큰 불일치");
            }

        }

        response.sendError(401, "토큰에러");
        return false;
    }

    private void accessTokenResponse(HttpServletResponse response, String accessToken) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JwtAccessTokenResponse jwtAccessTokenResponse = new JwtAccessTokenResponse("엑세스토큰 재발급", accessToken);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().write(mapper.writeValueAsString(jwtAccessTokenResponse));
    }
}
