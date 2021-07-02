package udodog.goGetterServer.service.event;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.event.EventCreateRequestDto;
import udodog.goGetterServer.model.dto.response.event.EventsResponseDto;
import udodog.goGetterServer.repository.EventRepository;
import udodog.goGetterServer.repository.querydsl.EventQueryRepository;


@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    private final EventQueryRepository eventQueryRepository;

    public DefaultRes eventCreate(EventCreateRequestDto request){
        eventRepository.save(request.toEntity());
        return DefaultRes.response(HttpStatus.OK.value(), "등록성공");
    }

    public DefaultRes<Page<EventsResponseDto>> progressEventFindAll(Pageable pageable){

        Page<EventsResponseDto> result = eventQueryRepository.progressEventFindAll(pageable);

        if(result.getTotalElements() == 0){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }
        return DefaultRes.response(HttpStatus.OK.value(), "조회성공", result);
    }

    public DefaultRes<Page<EventsResponseDto>> endEventFindAll(Pageable pageable){

        Page<EventsResponseDto> result = eventQueryRepository.endEventFindAll(pageable);

        if(result.getTotalElements() == 0){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }
        return DefaultRes.response(HttpStatus.OK.value(), "조회성공", result);
    }
}
