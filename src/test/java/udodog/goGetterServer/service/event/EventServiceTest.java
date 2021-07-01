package udodog.goGetterServer.service.event;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.event.EventCreateRequestDto;
import udodog.goGetterServer.model.entity.Event;
import udodog.goGetterServer.repository.EventRepository;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@Slf4j
public class EventServiceTest {

    @InjectMocks
    EventService eventService;

    @Mock
    EventRepository eventRepository;

    @Test
    public void 이벤트_생성(){

        //given
        String title = "신규 회원 등록 이벤트";
        String content = "20% 할인 쿠폰 지급";
        LocalDate startData = LocalDate.of(2021,7,1);
        LocalDate endDate = LocalDate.of(2021,7,15);
        String imgUrl = null;

        EventCreateRequestDto eventCreateRequestDto = new EventCreateRequestDto(title, content, startData, endDate, imgUrl);

        Event mockEvent = eventCreateRequestDto.toEntity();

        //when
        given(eventRepository.save(any())).willReturn(mockEvent);
        DefaultRes defaultRes = eventService.eventCreate(eventCreateRequestDto);

        //then
        assertThat(defaultRes.getMessage()).isEqualTo("등록성공");

    }


}
