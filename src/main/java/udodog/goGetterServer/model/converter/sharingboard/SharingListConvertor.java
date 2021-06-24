package udodog.goGetterServer.model.converter.sharingboard;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.api.sharingboard.SharingBoardController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.sharingboard.SimpleBoardResponse;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SharingListConvertor implements RepresentationModelAssembler<DefaultRes<List<SimpleBoardResponse>>
        , EntityModel<DefaultRes<List<SimpleBoardResponse>>>> {

    @Override
    public EntityModel<DefaultRes<List<SimpleBoardResponse>>> toModel(DefaultRes<List<SimpleBoardResponse>> defaultRes) {

        return EntityModel.of(defaultRes,
                linkTo(methodOn(SharingBoardController.class).getBoardList(null)).withRel("list"));
    }
}