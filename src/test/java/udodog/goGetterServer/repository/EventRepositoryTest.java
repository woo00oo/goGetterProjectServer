package udodog.goGetterServer.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import udodog.goGetterServer.model.entity.Event;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    void 이벤트_저장(){

        //given
        Event event = Event.builder()
                .title("서비스 오픈 기념")
                .content("안녕하세요")
                .start_date(LocalDate.of(2021,06,16))
                .end_date(LocalDate.of(2021,07,16))
                .build();

        //when
        Event saveEvent = eventRepository.save(event);

        //then
        assertThat(event).isEqualTo(saveEvent);

    }
}
