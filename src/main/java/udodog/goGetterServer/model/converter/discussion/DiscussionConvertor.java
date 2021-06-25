package udodog.goGetterServer.model.converter.discussion;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.api.discussion.DiscussionController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.discussion.DiscussionDetailResponse;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DiscussionConvertor implements RepresentationModelAssembler<DefaultRes<DiscussionDetailResponse>, EntityModel<DefaultRes<DiscussionDetailResponse>>> {

    @Override
    public EntityModel<DefaultRes<DiscussionDetailResponse>> toModel(DefaultRes<DiscussionDetailResponse> entity) {

        return EntityModel.of(entity,
                linkTo(methodOn(DiscussionController.class).getDetailBoard(null)).withRel("detail"),
                linkTo(methodOn(DiscussionController.class).updateBoard(null, null)).withRel("update"),
                linkTo(methodOn(DiscussionController.class).deleteBoard(null)).withRel("delete"));
    }
}
