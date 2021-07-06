package udodog.goGetterServer.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import udodog.goGetterServer.config.JpaAuditingConfig;
import udodog.goGetterServer.model.entity.SharingBoard;
import udodog.goGetterServer.model.entity.SharingBoardReply;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.model.enumclass.UserGrade;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = JpaAuditingConfig.class
))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SharingBoardReplyRepositoryTest {

    @Autowired private  UserRepository userRepository;
    @Autowired private SharingBoardRepository sharingBoardRepository;
    @Autowired private SharingBoardReplyRepository sharingBoardReplyRepository;

    @Test
    @Rollback(false)
    @DisplayName("SharingBoardReply Repository save Test")
    void saveSharingBoardReply(){
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

        SharingBoardReply sharingBoardReply = SharingBoardReply.
                                              builder().
                                              user(saveUser).
                                              sharingBoard(saveSharingBoard).
                                              comment("Sharing Board Reply Test Comment").
                                              build();

        //when
        SharingBoardReply saveSharingBoardReply = sharingBoardReplyRepository.save(sharingBoardReply);
        saveSharingBoard.getSharingBoardReplyList().add(saveSharingBoardReply);


        //then
        assertThat(saveSharingBoardReply).isEqualTo(sharingBoardReply);
        assertTrue(saveSharingBoard.getSharingBoardReplyList().contains(saveSharingBoardReply));
    }

}