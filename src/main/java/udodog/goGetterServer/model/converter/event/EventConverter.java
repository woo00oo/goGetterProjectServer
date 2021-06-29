package udodog.goGetterServer.model.converter.event;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.api.event.EventController;
import udodog.goGetterServer.model.dto.DefaultRes;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EventConverter implements RepresentationModelAssembler<DefaultRes, EntityModel<DefaultRes>> {

    @Override
    public EntityModel<DefaultRes> toModel(DefaultRes entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(EventController.class).eventCreate(null)).withRel("event-create"));
    }

}
