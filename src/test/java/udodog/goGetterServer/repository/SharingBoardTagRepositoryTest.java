package udodog.goGetterServer.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import udodog.goGetterServer.config.JpaAuditingConfig;
import udodog.goGetterServer.model.dto.request.sharingboard.CreateBoardRequest;
import udodog.goGetterServer.model.entity.SharingBoard;
import udodog.goGetterServer.model.entity.SharingBoardTag;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.model.enumclass.UserGrade;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = JpaAuditingConfig.class
))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SharingBoardTagRepositoryTest {

    @Autowired private SharingBoardRepository sharingBoardRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private SharingBoardTagRepository sharingBoardTagRepository;

    @Test
    @DisplayName("SharingBoard Tag Repository save Test")
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

        List<String> tagList = new LinkedList<>();
        tagList.add("태그 1");
        tagList.add("태그 2");

        CreateBoardRequest request = new CreateBoardRequest(user.getId(), "Sharing Board Test Title", "Sharing Board Test Content","book Title",tagList);

        SharingBoard sharingBoard = new SharingBoard(request, Optional.of(saveUser));
        //when
        SharingBoard saveSharingBoard = sharingBoardRepository.save(sharingBoard);

        //then
        assertThat(saveSharingBoard).isEqualTo(sharingBoard);
    }

}