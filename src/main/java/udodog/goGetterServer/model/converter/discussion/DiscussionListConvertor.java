package udodog.goGetterServer.model.converter.discussion;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.api.discussion.DiscussionController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.discussion.DetailDiscussionResponse;
import udodog.goGetterServer.model.dto.response.discussion.ListDiscussionResponse;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DiscussionListConvertor implements RepresentationModelAssembler<DefaultRes<List>,
        EntityModel<DefaultRes<List>>> {

    @Override
    public EntityModel<DefaultRes<List>> toModel(DefaultRes<List> defaultRes) {
        return EntityModel.of(defaultRes,
                linkTo(methodOn(DiscussionController.class).getBoardList(null)).withSelfRel(),
                linkTo(methodOn(DiscussionController.class).getBoardList(null)).withRel("insert"));
    }
}