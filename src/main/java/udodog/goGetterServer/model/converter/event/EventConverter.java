package udodog.goGetterServer.model.converter.event;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.api.event.EventController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.event.ProgressEventsResponseDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EventConverter implements RepresentationModelAssembler<DefaultRes<Page<ProgressEventsResponseDto>>, EntityModel<DefaultRes<Page<ProgressEventsResponseDto>>>> {

    @Override
    public EntityModel<DefaultRes<Page<ProgressEventsResponseDto>>> toModel(DefaultRes<Page<ProgressEventsResponseDto>> entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(EventController.class).eventCreate(null)).withRel("event-create"),
                linkTo(methodOn(EventController.class).progressEventFindAll(null)).withRel("progressEvent-Find-All"));
    }

}
