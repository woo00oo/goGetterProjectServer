package udodog.goGetterServer.model.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import udodog.goGetterServer.model.entity.User;

@Getter
@RequiredArgsConstructor
public class BlackMemberManagementResDto {

    private User user;

    private String name;

    private String nickName;

    private String email;

    private String phoneNumber;

    public BlackMemberManagementResDto(User user) { // User 객체를 받기 위한 생성자
        this.user = user;
        this.name = user.getName();
        this.nickName = user.getNickName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
    } // 생성자 끝

} // Class 끝
