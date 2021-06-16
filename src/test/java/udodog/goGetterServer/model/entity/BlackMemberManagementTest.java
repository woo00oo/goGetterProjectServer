package udodog.goGetterServer.model.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import udodog.goGetterServer.model.enumclass.UserGrade;
import udodog.goGetterServer.repository.BlackMemberManagementRepository;
import udodog.goGetterServer.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BlackMemberManagementTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlackMemberManagementRepository blackMemberManagementRepository;

    @Test
    void 블랙_회원_등록() {

          // given → 테스트를 준비하는 과정 (~가 주어지고)
        User user = User.builder()
                .email("junyharang8592@gmail.com")
                .password("1234")
                .name("주니하랑")
                .nickName("JunyHarang")
                .phoneNumber("010-1234-5678")
                .grade(UserGrade.USER)
                .build();

        User saveUser = userRepository.save(user);

        BlackMemberManagement blackMemberManagement = BlackMemberManagement.builder()
                .userId(saveUser)
                .build();



          // when → 실제로 테스트를 실행하는 과정 (~을 했을 때)
        BlackMemberManagement saveBlackMemberManagement = blackMemberManagementRepository.save(blackMemberManagement);

          // then → 테스트를 검증하는 과정 (~한 값이 나와야 함.)
        assertThat(blackMemberManagement).isEqualTo(saveBlackMemberManagement);
    } // Method 끝

}