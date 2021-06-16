package udodog.goGetterServer.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import udodog.goGetterServer.model.entity.MessageNotificationOff;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.model.entity.enumclass.UserGrade;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MessageNotificationOffRepositoryTest {

    @Autowired private  UserRepository userRepository;
    @Autowired private  MessageNotificationOffRepository messageNotificationOffRepository;

    @Test
    @DisplayName("Message Notification Off Repository Save Test")
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

        MessageNotificationOff Notification = MessageNotificationOff.
                                              builder().
                                              receiver(receiver).
                                              sender(sender).
                                              build();

        //when
        MessageNotificationOff saveNotification = messageNotificationOffRepository.save(Notification);

        //then
        assertThat(saveNotification).isEqualTo(Notification);
    }
}