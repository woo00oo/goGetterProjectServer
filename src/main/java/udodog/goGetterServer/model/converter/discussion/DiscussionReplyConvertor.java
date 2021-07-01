package udodog.goGetterServer.model.converter.discussion;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.api.discussion.DiscussionReplyController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.discussion.DiscussionReplyResponse;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DiscussionReplyConvertor implements RepresentationModelAssembler<DefaultRes<DiscussionReplyResponse>,
        EntityModel<DefaultRes<DiscussionReplyResponse>>> {

    @Override
    public EntityModel<DefaultRes<DiscussionReplyResponse>> toModel(DefaultRes<DiscussionReplyResponse> entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(DiscussionReplyController.class).createReply(null)).withRel("replyInsert"),
                linkTo(methodOn(DiscussionReplyController.class).updateReply(null,null)).withRel("replyUpdate"),
                linkTo(methodOn(DiscussionReplyController.class).deleteReply(null, null)).withRel("replyDelete"));
    }
}
