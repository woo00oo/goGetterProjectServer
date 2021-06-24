package udodog.goGetterServer.model.converter.discussion;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.api.discussion.DiscussionController;
import udodog.goGetterServer.model.dto.DefaultRes;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DiscussionListConvertor implements RepresentationModelAssembler<DefaultRes<List>,
        EntityModel<DefaultRes<List>>> {

    @Override
    public EntityModel<DefaultRes<List>> toModel(DefaultRes<List>defaultRes) {
        return EntityModel.of(defaultRes,
                linkTo(methodOn(DiscussionController.class).getBoardList(null)).withRel("list"));
    }

}