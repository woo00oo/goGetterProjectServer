package udodog.goGetterServer.service.discussion;

import lombok.RequiredArgsConstructor;
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
import udodog.goGetterServer.model.entity.DiscussionBoardReadhit;
import udodog.goGetterServer.repository.DiscussionBoardReplyRepository;
import udodog.goGetterServer.repository.DiscussionBoardRepository;
import udodog.goGetterServer.repository.DiscussionBoardReadhitRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiscussionService {

    private final DiscussionBoardRepository discussionBoardRepository;
    private final DiscussionBoardReplyRepository replyRepository;
    private final DiscussionBoardReadhitRepository readhitRepository;


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
                .map(board -> new DiscussionReseponseDto(board, readhitRepository.findByDiscussionId(board.getId())))
                .collect(Collectors.toList());
    }

    // 게시글 상세 보기
    public DefaultRes<DiscussionDetailResponse> getDetailBoard(Long id) {   // 게시판 id

        Optional<DiscussionBoard> discussionBoard = discussionBoardRepository.findById(id);
        Optional<DiscussionBoardReadhit> readhit = readhitRepository.findByDiscussionId(id);

        // 조회수 카운트 증가
        readhit.get().incrementCount();

        // 상세페이지를 보게 되면 조회 수 증가 값을 update
        readhitRepository.updateCount(id, readhit.get().getCount());

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

    // 게시글 등록
    public DefaultRes insertBoard(DiscussionInsertRequestDto requestDto) {  // 등록Dto

        if(requestDto == null){
            return DefaultRes.response(HttpStatus.OK.value(), "등록실패");
        }else {
            DiscussionBoard board = discussionBoardRepository.save(requestDto.toEntity(requestDto));

            DiscussionBoardReadhit readhit = DiscussionBoardReadhit.builder()
                    .discussionBoard(board)
                    .build();

            readhitRepository.save(readhit);

            return DefaultRes.response(HttpStatus.OK.value(), "등록성공");
        }
    }

    // 게시글 수정
    public DefaultRes updateBoard(DiscussionEditRequest update, Long id) { // 업데이트Dto, 게시글 번호

        Optional<DiscussionBoard> optionalBoard = discussionBoardRepository.findById(id);

        if(optionalBoard.isEmpty()) {
            DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }

        if(!(optionalBoard.get().getUser().getId().equals(update.getUserId()))){
            return DefaultRes.response(HttpStatus.OK.value(), "수정실패");
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
    public DefaultRes delete(Long id, Long userId) { // 게시글 번호, 유저번호
        Optional<DiscussionBoard> optionalBoard = discussionBoardRepository.findById(id);

        if (optionalBoard.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }

        if(!(optionalBoard.get().getId().equals(id))){
            return DefaultRes.response(HttpStatus.OK.value(), "삭제실패");
        }

        if(!(optionalBoard.get().getUser().getId().equals(userId))){
            return DefaultRes.response(HttpStatus.OK.value(), "삭제실패");
        }

        readhitRepository.deleteByDiscussionId(optionalBoard.get().getId());
        replyRepository.deleteByDiscussionId(optionalBoard.get().getId());
        discussionBoardRepository.deleteById(id, userId);

        return DefaultRes.response(HttpStatus.OK.value(), "삭제성공");
    }

    // 제목 검색
    public DefaultRes<List<DiscussionReseponseDto>> searchTitle(String title, Pageable pageable) {

        Page<DiscussionBoard> boardPage = discussionBoardRepository.findByTitleContaining(title, pageable);

        if(boardPage.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }

        return DefaultRes.response(HttpStatus.OK.value(), "검색성공", searchTitleResult(boardPage), new Pagination(boardPage));
    }

    private List<DiscussionReseponseDto> searchTitleResult(Page<DiscussionBoard> boardPage) {

        return boardPage.stream()
                .map(board -> new DiscussionReseponseDto(board, readhitRepository.findById(board.getId())))
                .collect(Collectors.toList());
    }

    // 내용 검색
    public DefaultRes<List<DiscussionReseponseDto>> searchContent(String content, Pageable pageable) {

        Page<DiscussionBoard> boardPage = discussionBoardRepository.findByContentContaining(content, pageable);

        if(boardPage.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }

        return DefaultRes.response(HttpStatus.OK.value(), "검색성공", searchContentResult(boardPage), new Pagination(boardPage));
    }

    private List<DiscussionReseponseDto> searchContentResult(Page<DiscussionBoard> boardPage) {

        return boardPage.stream()
                .map(board -> new DiscussionReseponseDto(board, readhitRepository.findById(board.getId())))
                .collect(Collectors.toList());
    }

    // 제목 + 내용 검색
    public DefaultRes<List<DiscussionReseponseDto>> searchAll(String search, Pageable pageable) {
        Page<DiscussionBoard> boardPage = discussionBoardRepository.findByAllContaining(search, pageable);

        if(boardPage.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }

        return DefaultRes.response(HttpStatus.OK.value(), "검색성공", searchAllResult(boardPage), new Pagination(boardPage));
    }

    private List<DiscussionReseponseDto> searchAllResult(Page<DiscussionBoard> boardPage) {

        return boardPage.stream()
                .map(board -> new DiscussionReseponseDto(board, readhitRepository.findById(board.getId())))
                .collect(Collectors.toList());
    }
}
