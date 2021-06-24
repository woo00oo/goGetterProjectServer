package udodog.goGetterServer.model.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import udodog.goGetterServer.model.entity.User;

@Getter
@RequiredArgsConstructor
public class BlackMemberManagementResDto {

<<<<<<< Updated upstream
    private User user;
=======
    private Long userId;
>>>>>>> Stashed changes

    private String name;

    private String nickName;

    private String email;

    private String phoneNumber;

    public BlackMemberManagementResDto(User user) { // User 객체를 받기 위한 생성자
<<<<<<< Updated upstream
        this.user = user;
=======
        this.userId = user.getId();
>>>>>>> Stashed changes
        this.name = user.getName();
        this.nickName = user.getNickName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
    } // 생성자 끝

} // Class 끝
