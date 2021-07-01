package udodog.goGetterServer.model.dto.response.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import udodog.goGetterServer.model.enumclass.UserGrade;

@Getter
@NoArgsConstructor
public class UserSignInResponseDto {

    private String accessToken;

    private String refreshToken;

    private Long userId;

    private UserGrade userGrade;

    public UserSignInResponseDto(String accessToken, String refreshToken, Long userId, UserGrade userGrade) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.userId = userId;
        this.userGrade = userGrade;
    }
}
