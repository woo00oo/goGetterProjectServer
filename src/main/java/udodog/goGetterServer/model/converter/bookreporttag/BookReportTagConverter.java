package udodog.goGetterServer.model.converter.bookreporttag;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.api.bookreporttag.BookReportTagController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.bookreporttag.BookReportTagResponseDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BookReportTagConverter implements RepresentationModelAssembler<DefaultRes<BookReportTagResponseDto>, EntityModel<DefaultRes<BookReportTagResponseDto>>> {
    @Override
    public EntityModel<DefaultRes<BookReportTagResponseDto>> toModel(DefaultRes<BookReportTagResponseDto> entity) {
        return EntityModel.of(entity, linkTo( methodOn( BookReportTagController.class ).insertTag(null, null)).withRel("insertTag"),
                                      linkTo( methodOn ( BookReportTagController.class ).bookReportTagUpdate(null, null, null)).withRel("updateTag"),
                                      linkTo( methodOn ( BookReportTagController.class ).bookReportTagDelete(null, null)).withRel("deleteTag"));
    } // toModel()끝
} // Class 끝
