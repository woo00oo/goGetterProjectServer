package udodog.goGetterServer.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import udodog.goGetterServer.model.entity.SharingBoard;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.model.enumclass.UserGrade;

import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SharingBoardRepositoryTest {

    @Autowired private SharingBoardRepository sharingBoardRepository;
    @Autowired private  UserRepository userRepository;

    private User user;
    private User saveUser;

    @BeforeEach
    @DisplayName("create User")
    void createUser(){
         user = User.builder().
                email("testEmail@gmail.com").
                phoneNumber("010-1234-5678").
                name("user1").
                nickName("user1Nickname").
                password("password").
                grade(UserGrade.USER).
                createdAt(LocalDate.now()).
                build();

         saveUser = userRepository.save(user);
    }

    @Test
    @DisplayName("SharingBoard Repository save Test")
    void saveSharingBoard(){
        //given
        SharingBoard sharingBoard = SharingBoard.builder().
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