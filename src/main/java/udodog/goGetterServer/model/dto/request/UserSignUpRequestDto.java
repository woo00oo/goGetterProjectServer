package udodog.goGetterServer.model.dto.request;

import lombok.Builder;
import lombok.Getter;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.model.enumclass.UserGrade;

import javax.validation.constraints.NotEmpty;

@Getter
public class UserSignUpRequestDto {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String name;

    @NotEmpty
    private String nickName;

    @NotEmpty
    private String phoneNumber;

    @Builder
    public User toEntity(){

        return User.builder()
                .email(email)
                .password(password)
                .name(name)
                .nickName(nickName)
                .phoneNumber(phoneNumber)
                .grade(UserGrade.USER)
                .build();

    }
}
