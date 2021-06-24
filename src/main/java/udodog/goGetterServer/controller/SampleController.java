package udodog.goGetterServer.controller;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import udodog.goGetterServer.model.converter.EntityToModelConvertor;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.ModelMapperTestDTO;
import udodog.goGetterServer.model.entity.Book;
import udodog.goGetterServer.model.entity.SharingBoard;


@Slf4j
@RestController
public class SampleController {

    @Autowired
    private EntityToModelConvertor entityToModelConvertor;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/sample")
    public ResponseEntity<EntityModel<DefaultRes<Book>>> getBook(){
        Book book = new Book();

        return ResponseEntity
                .ok(entityToModelConvertor.toModel(
                        DefaultRes.response(HttpStatus.OK.value(), "정상 조회되었습니다",book)
                ));

    }

    @GetMapping("/test-mapper")
    public ResponseEntity<DefaultRes<ModelMapperTestDTO>> getModelMapperTestDTO(){
        SharingBoard sharingBoard = new SharingBoard();
        ModelMapperTestDTO dto = modelMapper.map(sharingBoard, ModelMapperTestDTO.class);

        return ResponseEntity
                .ok(DefaultRes.response(HttpStatus.OK.value(), "정상 조회되었습니다",dto)
                );
    }

}
