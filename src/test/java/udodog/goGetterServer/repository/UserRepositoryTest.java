package udodog.goGetterServer.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.model.enumclass.UserGrade;


import static org.assertj.core.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class UserRepositoryTest{

    @Autowired
    private UserRepository userRepository;

    @Test
    void 회원_저장(){

        //given
        User user = User.builder()
                .email("hwoo00oo96@gmail.com")
                .password("1234")
                .name("변현우")
                .nickName("woo00oo")
                .phoneNumber("010-9245-7396")
                .grade(UserGrade.USER)
                .build();

        //when
        User saveUser = userRepository.save(user);

        //then
        assertThat(user).isEqualTo(saveUser);

    }
}
