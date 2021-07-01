package udodog.goGetterServer.service.event;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.event.EventCreateRequestDto;
import udodog.goGetterServer.repository.EventRepository;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public DefaultRes eventCreate(EventCreateRequestDto request){
        eventRepository.save(request.toEntity());
        return DefaultRes.response(HttpStatus.OK.value(), "등록성공");
    }
}
