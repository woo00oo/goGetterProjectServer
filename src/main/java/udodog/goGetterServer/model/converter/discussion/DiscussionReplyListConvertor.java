package udodog.goGetterServer.model.converter.discussion;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.api.discussion.DiscussionReplyController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.discussion.DiscussionReplyResponse;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DiscussionReplyListConvertor implements RepresentationModelAssembler<DefaultRes<List<DiscussionReplyResponse>>,
        EntityModel<DefaultRes<List<DiscussionReplyResponse>>>> {

    @Override
    public EntityModel<DefaultRes<List<DiscussionReplyResponse>>> toModel(DefaultRes<List<DiscussionReplyResponse>> entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(DiscussionReplyController.class).getBoardReplyList(null, null)).withRel("list"),
                linkTo(methodOn(DiscussionReplyController.class).createReply(null, null,null)).withRel("insert"));
    }
}
