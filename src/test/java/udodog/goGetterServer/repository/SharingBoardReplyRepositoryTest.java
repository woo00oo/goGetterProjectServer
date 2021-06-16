package udodog.goGetterServer.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import udodog.goGetterServer.model.entity.SharingBoard;
import udodog.goGetterServer.model.entity.SharingBoardReply;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.model.entity.enumclass.UserGrade;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SharingBoardReplyRepositoryTest {

    @Autowired private  UserRepository userRepository;
    @Autowired private SharingBoardRepository sharingBoardRepository;
    @Autowired private SharingBoardReplyRepository sharingBoardReplyRepository;

    private User user;
    private User saveUser;

    private SharingBoard sharingBoard;
    private SharingBoard saveSharingBoard;


    @BeforeEach
    @DisplayName("create User & Sharing Board")
    void createUserAndSharingBoard(){
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

        sharingBoard = SharingBoard.builder().
                user(saveUser).
                content("Sharing Board Test Content").
                title("Sharing Board Test Title").
                build();

        saveSharingBoard = sharingBoardRepository.save(sharingBoard);
    }

    @Test
    @DisplayName("SharingBoardReply Repository save Test")
    void saveSharingBoardReply(){
        //given
        SharingBoardReply sharingBoardReply = SharingBoardReply.
                                              builder().
                                              user(saveUser).
                                              sharingBoard(saveSharingBoard).
                                              comment("Sharing Board Reply Test Comment").
                                              build();

        //when
        SharingBoardReply saveSharingBoardReply = sharingBoardReplyRepository.save(sharingBoardReply);

        //then
        assertThat(saveSharingBoardReply).isEqualTo(sharingBoardReply);
    }

}