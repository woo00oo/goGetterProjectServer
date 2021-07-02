package udodog.goGetterServer.model.converter.discussion;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.api.discussion.DiscussionController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.discussion.DiscussionReseponseDto;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DiscussionListConvertor implements RepresentationModelAssembler<DefaultRes<List<DiscussionReseponseDto>>,
        EntityModel<DefaultRes<List<DiscussionReseponseDto>>>> {

    @Override
    public EntityModel<DefaultRes<List<DiscussionReseponseDto>>> toModel(DefaultRes<List<DiscussionReseponseDto>>defaultRes) {

        return EntityModel.of(defaultRes,
                linkTo(methodOn(DiscussionController.class).getBoardList(null)).withRel("list"),
                linkTo(methodOn(DiscussionController.class).getDetailBoard(null)).withRel("detail"),
                linkTo(methodOn(DiscussionController.class).insertBoard(null, null)).withRel("insert"),
                linkTo(methodOn(DiscussionController.class).searchTitle(null, null)).withRel("searchTitle"),
                linkTo(methodOn(DiscussionController.class).searchContent(null, null)).withRel("searchContent"),
                linkTo(methodOn(DiscussionController.class).searchAll(null, null)).withRel("searchAll"));
    }

}