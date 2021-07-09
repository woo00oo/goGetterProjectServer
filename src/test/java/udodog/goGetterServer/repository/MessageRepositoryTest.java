package udodog.goGetterServer.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import udodog.goGetterServer.config.JpaAuditingConfig;
import udodog.goGetterServer.model.entity.Message;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.model.enumclass.UserGrade;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = JpaAuditingConfig.class
))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MessageRepositoryTest {

    @Autowired private  UserRepository userRepository;
    @Autowired private  MessageRepository messageRepository;

    @Test
    @DisplayName("Message Repository Save Test")
    void saveMessage(){

        //given
        User user1 = User.builder().
                email("testEmail@gmail.com").
                phoneNumber("010-1234-5678").
                name("sender").
                nickName("user1Nickname").
                password("password").
                grade(UserGrade.USER).
                build();

        User user2 = User.builder().
                email("testEmail@gmail.com").
                phoneNumber("010-1234-5678").
                name("receiver").
                nickName("user2Nickname").
                password("password").
                grade(UserGrade.USER).
                build();

        User sender = userRepository.save(user1);
        User receiver = userRepository.save(user2);

        Message message = Message.builder().
                          sender(sender).
                          receiver(receiver).
                          content("Message Test Content").
                          build();

        //when
        Message saveMessage = messageRepository.save(message);

        //then
        assertThat(saveMessage).isEqualTo(message);
    }

}