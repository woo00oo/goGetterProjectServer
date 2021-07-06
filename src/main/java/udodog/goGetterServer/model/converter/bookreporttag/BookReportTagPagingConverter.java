package udodog.goGetterServer.model.converter.bookreporttag;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.api.bookreporttag.BookReportTagController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.bookreporttag.BookReportTagResponseDto;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BookReportTagPagingConverter implements RepresentationModelAssembler<DefaultRes<Page<BookReportTagResponseDto>>, EntityModel<DefaultRes<Page<BookReportTagResponseDto>>>> {
    @Override
    public EntityModel<DefaultRes<Page<BookReportTagResponseDto>>> toModel(DefaultRes<Page<BookReportTagResponseDto>> entity) {
        return EntityModel.of(entity, linkTo ( methodOn ( BookReportTagController.class ).seartchBookReportTagPaging(null)).withRel("paging"),
                                      linkTo ( methodOn ( BookReportTagController.class ).insertTag(null, null)).withRel("insert"));
    } // toModel() 끝
} // Class 끝
