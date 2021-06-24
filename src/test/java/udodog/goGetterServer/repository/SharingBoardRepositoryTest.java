package udodog.goGetterServer.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import udodog.goGetterServer.config.JpaAuditingConfig;
import udodog.goGetterServer.model.entity.SharingBoard;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.model.enumclass.UserGrade;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = JpaAuditingConfig.class
))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SharingBoardRepositoryTest {

    @Autowired private SharingBoardRepository sharingBoardRepository;
    @Autowired private  UserRepository userRepository;

    @Test
    @DisplayName("SharingBoard Repository save Test")
    void saveSharingBoard(){

        //given
        User user = User.builder().
                email("testEmail@gmail.com").
                phoneNumber("010-1234-5678").
                name("user1").
                nickName("user1Nickname").
                password("password").
                grade(UserGrade.USER).
                build();

        User saveUser = userRepository.save(user);

        SharingBoard sharingBoard = SharingBoard.
                                    builder().
                                    user(saveUser).
                                    content("Sharing Board Test Content").
                                    title("Sharing Board Test Title").
                                    build();
        //when
        SharingBoard saveSharingBoard = sharingBoardRepository.save(sharingBoard);

        //then
        assertThat(saveSharingBoard).isEqualTo(sharingBoard);
    }

}