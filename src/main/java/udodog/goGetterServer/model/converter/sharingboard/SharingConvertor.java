package udodog.goGetterServer.model.converter.sharingboard;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.api.sharingboard.SharingBoardController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.sharingboard.BoardResponse;
import udodog.goGetterServer.model.dto.response.sharingboard.SimpleBoardResponse;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SharingConvertor implements RepresentationModelAssembler<DefaultRes<BoardResponse>
        , EntityModel<DefaultRes<BoardResponse>>> {

    @Override
    public EntityModel<DefaultRes<BoardResponse>> toModel(DefaultRes<BoardResponse> defaultRes) {
        return EntityModel.of(defaultRes,
                linkTo(methodOn(SharingBoardController.class).getBoardDetail(null)).withSelfRel()
                ,linkTo(methodOn(SharingBoardController.class).getBoardList(null)).withRel("list"));
    }


}
