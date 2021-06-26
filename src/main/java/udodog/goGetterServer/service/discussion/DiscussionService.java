package udodog.goGetterServer.service.discussion;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.Pagination;
import udodog.goGetterServer.model.dto.request.discussion.DiscussionEditRequest;
import udodog.goGetterServer.model.dto.request.discussion.DiscussionInsertRequestDto;
import udodog.goGetterServer.model.dto.response.discussion.DiscussionDetailResponse;
import udodog.goGetterServer.model.dto.response.discussion.DiscussionReseponseDto;
import udodog.goGetterServer.model.entity.DiscussionBoard;
import udodog.goGetterServer.repository.DiscussonBoardRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiscussionService {

    private final DiscussonBoardRepository discussionBoardRepository;
    private final ModelMapper mapper;

    // 전체 목록 조회
    public DefaultRes<List<DiscussionReseponseDto>> getBoardList(Pageable pageable) {// 페이징 변수

        Page<DiscussionBoard> discussionBoardPage = discussionBoardRepository.findAllWithFetchJoin(pageable);

        if(discussionBoardPage.getTotalElements() == 0){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
       }else{
            return DefaultRes.response(HttpStatus.OK.value(), "조회성공", data(discussionBoardPage), new Pagination(discussionBoardPage));
        }
    }

    private List<DiscussionReseponseDto> data(Page<DiscussionBoard> discussionBoardPage) {

       return discussionBoardPage.stream()
                .map(DiscussionReseponseDto :: new)
                .collect(Collectors.toList());
    }

    // 게시글 상세 보기
    public DefaultRes<DiscussionDetailResponse> getDetailBoard(Long id) {   // 게시판 id

        Optional<DiscussionBoard> discussionBoard = discussionBoardRepository.findById(id);

        if(discussionBoard.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }else {
            return DefaultRes.response(HttpStatus.OK.value(), "상세보기성공", detailData(discussionBoard));
        }
    }

    private DiscussionDetailResponse detailData(Optional<DiscussionBoard> discussionBoard) {

        DiscussionDetailResponse discussionDetailResponse = discussionBoard.map(DiscussionDetailResponse::new)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다."));

        return discussionDetailResponse;
    }

    public DefaultRes insertBoard(DiscussionInsertRequestDto requestDto) {

        if(requestDto == null){
            return DefaultRes.response(HttpStatus.OK.value(), "등록실패");
        }else {
            discussionBoardRepository.save(requestDto.toEntity(requestDto));
            return DefaultRes.response(HttpStatus.OK.value(), "등록성공");
        }
    }

    // 게시글 수정
    public DefaultRes updateBoard(DiscussionEditRequest update, Long id) {

        Optional<DiscussionBoard> optionalBoard = discussionBoardRepository.findById(id);

        if(optionalBoard.isEmpty()){
            DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }

        DiscussionBoard updateBoard = optionalBoard.get().updateBoard(update);
        DiscussionBoard saveboard = discussionBoardRepository.save(updateBoard);

        if(saveboard.getId() == optionalBoard.get().getId()){
            return DefaultRes.response(HttpStatus.OK.value(), "수정성공");
        }else {
            return DefaultRes.response(HttpStatus.OK.value(), "수정실패");
        }
    }

    // 게시글 삭제
    public DefaultRes delete(Long id) {
        Optional<DiscussionBoard> optionalBoard = discussionBoardRepository.findById(id);

        if (optionalBoard.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }

        if(optionalBoard.get().getId() != id){
            return DefaultRes.response(HttpStatus.OK.value(), "삭제실패");
        }

        discussionBoardRepository.deleteById(id);

        return DefaultRes.response(HttpStatus.OK.value(), "삭제성공");
    }
}
