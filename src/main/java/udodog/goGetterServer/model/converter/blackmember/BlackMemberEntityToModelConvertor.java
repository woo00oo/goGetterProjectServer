package udodog.goGetterServer.model.converter.blackmember;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import udodog.goGetterServer.controller.api.blackmember.BlackMemberManagementController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.response.BlackMemberManagementResDto;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BlackMemberEntityToModelConvertor implements RepresentationModelAssembler<DefaultRes<List<BlackMemberManagementResDto>>, EntityModel<DefaultRes<List<BlackMemberManagementResDto>>>> {

    @Override
    public EntityModel<DefaultRes<List<BlackMemberManagementResDto>>> toModel(DefaultRes<List<BlackMemberManagementResDto>> defaultRes) {
        return EntityModel.of(defaultRes,
                linkTo(methodOn(BlackMemberManagementController.class).searchBlackMember(null)).withSelfRel(),
                linkTo(methodOn(BlackMemberManagementController.class).searchBlackMember(null)).withRel("seach"));
    } // toModel() 끝
} // Class 끝
