package udodog.goGetterServer.controller.api.event;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udodog.goGetterServer.model.converter.event.EventConverter;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.event.EventCreateRequestDto;
import udodog.goGetterServer.model.dto.request.event.EventUpdateRequestDto;
import udodog.goGetterServer.model.dto.response.event.DetailEventResponseDto;
import udodog.goGetterServer.model.dto.response.event.EventsResponseDto;
import udodog.goGetterServer.service.event.EventService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Api(tags = {"이벤트 관련 API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EventController {

    private final EventService eventService;

    private final EventConverter eventConverter;

    @ApiOperation(value = "이벤트 등록 API",notes = "관리자가 이벤트 등록시 사용되는 API 입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 등록성공")
    })
    @PostMapping("/admin/events")
    public ResponseEntity<DefaultRes> eventCreate(
            @ApiParam(value = "필수 : title, content, start_data, end_data" +
                    "선택 : img_url")
            @Valid @RequestBody EventCreateRequestDto request
            ){
        return new ResponseEntity<>(eventService.eventCreate(request), HttpStatus.OK);
    }

    @ApiOperation(value = "진행 중인 이벤트 전체 조회 API",notes = "현재 진행중인 이벤트들을 최신 순으로 조회 합니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 조회성공\n 2. 데이터없음")
    })
    @GetMapping("/events")
    public ResponseEntity<EntityModel<DefaultRes<Page<EventsResponseDto>>>> progressEventFindAll(
            @PageableDefault(sort = "startDate", direction = Sort.Direction.DESC, size = 12) Pageable pageable
    ){
        return new ResponseEntity<>(eventConverter.toModel(eventService.progressEventFindAll(pageable)), HttpStatus.OK);
    }

    @ApiOperation(value = "종료된 이벤트 전체 조회 API",notes = "종료된 이벤트들을 최신 순으로 조회 합니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 조회성공\n 2. 데이터없음")
    })
    @GetMapping("/end-events")
    public ResponseEntity<EntityModel<DefaultRes<Page<EventsResponseDto>>>> endEventFindAll(
            @PageableDefault(sort = "startDate", direction = Sort.Direction.DESC, size = 12) Pageable pageable
    ){
        return new ResponseEntity<>(eventConverter.toModel(eventService.endEventFindAll(pageable)), HttpStatus.OK);
    }

    @ApiOperation(value = "진행중인 이벤트 상세 조회 API",notes = "진행중인 이벤트를 상세 조회 합니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 조회성공\n 2. 데이터없음")
    })
    @GetMapping("/events/{eventId}")
    public ResponseEntity<DefaultRes<DetailEventResponseDto>> eventDetailFind(
            @PathVariable("eventId") Long eventId
    ){
        return new ResponseEntity<>(eventService.eventDetailFind(eventId), HttpStatus.OK);
    }

    @ApiOperation(value = "이벤트 업데이트 API",notes = "관리자는 이벤트를 업데이트 합니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 업데이트성공\n 2. 데이터없음")
    })
    @PatchMapping("/admin/events/{eventId}")
    public ResponseEntity eventUpdate(
            @PathVariable("eventId") Long eventId,
            @Valid @RequestBody EventUpdateRequestDto request,
            HttpServletResponse response
    ) throws IOException {
        String redirect_uri = "/api/events/" + eventId;
        DefaultRes result = eventService.eventUpdate(eventId, request);

        if(result.getStatusCode() == HttpStatus.SEE_OTHER.value()){
            response.sendRedirect(redirect_uri);
            return new ResponseEntity(result, HttpStatus.SEE_OTHER);
        }else{
            return new ResponseEntity(result, HttpStatus.OK);
        }
    }
}
