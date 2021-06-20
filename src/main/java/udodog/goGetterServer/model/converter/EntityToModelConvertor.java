package udodog.goGetterServer.model.converter;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.SampleController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.entity.Book;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EntityToModelConvertor implements RepresentationModelAssembler<DefaultRes<Book>, EntityModel<DefaultRes<Book>>> {

    @Override
    public EntityModel<DefaultRes<Book>> toModel(DefaultRes<Book> defaultRes) {
        return EntityModel.of(defaultRes,
                linkTo(methodOn(SampleController.class).getBook()).withSelfRel(),
                linkTo(methodOn(SampleController.class).getBook()).withRel("delete"));
    }

}
