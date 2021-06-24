package udodog.goGetterServer.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import udodog.goGetterServer.config.JpaAuditingConfig;
import udodog.goGetterServer.model.entity.SharingBoardLike;
import udodog.goGetterServer.model.entity.SharingBoard;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.model.enumclass.UserGrade;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = JpaAuditingConfig.class
))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SharingBoardSharingBoardLikeRepositoryTest {

    @Autowired private UserRepository userRepository;
    @Autowired private SharingBoardRepository sharingBoardRepository;
    @Autowired private SharingBoardLikeRepository sharingBoardLikeRepository;

    @Test
    @DisplayName("Like Repository Save Test")
    void saveLike(){

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

        SharingBoard sharingBoard = SharingBoard.builder().
                user(saveUser).
                content("Sharing Board Test Content").
                title("Sharing Board Test Title").
                build();

        SharingBoard saveSharingBoard = sharingBoardRepository.save(sharingBoard);


        SharingBoardLike sharingBoardLike = SharingBoardLike.
                    builder().
                    user(saveUser).
                    sharingBoard(saveSharingBoard).
                    build();

        //when
        SharingBoardLike saveSharingBoardLike = sharingBoardLikeRepository.save(sharingBoardLike);

        //then
        assertThat(saveSharingBoardLike).isEqualTo(sharingBoardLike);

    }


}