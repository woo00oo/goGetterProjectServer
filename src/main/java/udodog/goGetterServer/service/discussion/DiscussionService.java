package udodog.goGetterServer.service.discussion;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.Pagination;
import udodog.goGetterServer.model.dto.response.discussion.DetailDiscussionResponse;
import udodog.goGetterServer.model.dto.response.discussion.ListDiscussionResponse;
import udodog.goGetterServer.model.entity.DiscussionBoard;
import udodog.goGetterServer.repository.DiscussonBoardRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiscussionService {

    private final DiscussonBoardRepository discussionBoardRepository;
    private final ModelMapper mapper;

    public DefaultRes<List> getBoardList(Pageable pageable) {
        Page<DiscussionBoard> discussionBoardPage = discussionBoardRepository.findAll(pageable);

        if(discussionBoardPage.getTotalElements() == 0){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
       }else{
            return DefaultRes.response(HttpStatus.OK.value(), "조회성공", data(discussionBoardPage), new Pagination(discussionBoardPage));
        }
    }

    private List<ListDiscussionResponse> data(Page<DiscussionBoard> discussionBoardPage) {

        List<ListDiscussionResponse> discussionBoardList = discussionBoardPage.stream()
                .map(list -> mapper.map(list, ListDiscussionResponse.class))
                .collect(Collectors.toList());

        return discussionBoardList;
    }
}
