package udodog.goGetterServer.controller.api.event;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import udodog.goGetterServer.model.converter.event.EventConverter;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.event.EventCreateRequestDto;
import udodog.goGetterServer.service.event.EventService;

import javax.validation.Valid;

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
    public ResponseEntity<EntityModel<DefaultRes>> eventCreate(
            @ApiParam(value = "필수 : title, content, start_data, end_data" +
                    "선택 : img_url")
            @Valid @RequestBody EventCreateRequestDto request
            ){
        return new ResponseEntity<>(eventConverter.toModel(eventService.eventCreate(request)), HttpStatus.OK);
    }


}
