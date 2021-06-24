package udodog.goGetterServer.service.discussion;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.Pagination;
import udodog.goGetterServer.model.dto.response.discussion.DiscussionReseponseDto;
import udodog.goGetterServer.model.entity.DiscussionBoard;
import udodog.goGetterServer.repository.DiscussonBoardRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiscussionService {

    private final DiscussonBoardRepository discussionBoardRepository;
    private final ModelMapper mapper;

    // 전체 목록 조회
    public DefaultRes<List<DiscussionReseponseDto>> getBoardList(Pageable pageable) {// 페이징 변수
        Page<DiscussionBoard> discussionBoardPage = discussionBoardRepository.findAll(pageable);

        if(discussionBoardPage.getTotalElements() == 0){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
       }else{
            return DefaultRes.response(HttpStatus.OK.value(), "조회성공", data(discussionBoardPage), new Pagination(discussionBoardPage));
        }
    }

    private List<DiscussionReseponseDto> data(Page<DiscussionBoard> discussionBoardPage) {

        // ModelMapper를 이용해서 Entity To Dto Converter
        List<DiscussionReseponseDto> discussionBoardList = discussionBoardPage.stream()
                .map(list -> mapper.map(list, DiscussionReseponseDto.class))
                .collect(Collectors.toList());

        return discussionBoardList;
    }
}
