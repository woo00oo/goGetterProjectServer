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
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.repository.DiscussionBoardReadhitRepository;
import udodog.goGetterServer.repository.DiscussionBoardRepository;
import udodog.goGetterServer.repository.UserRepository;
import udodog.goGetterServer.repository.querydsl.DiscussionBoardQueryRepository;
import udodog.goGetterServer.repository.querydsl.DiscussionBoardReadhitQueryRepository;
import udodog.goGetterServer.repository.querydsl.DiscussionBoardReplyQueryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiscussionService {

    private final DiscussionBoardRepository discussionBoardRepository;
    private final DiscussionBoardReadhitRepository readhitRepository;
    private final UserRepository userRepository;
    private final DiscussionBoardQueryRepository queryRepository;
    private final DiscussionBoardReplyQueryRepository replyQueryRepository;
    private final DiscussionBoardReadhitQueryRepository readhitQueryRepository;


    // 전체 목록 조회
    public DefaultRes<List<DiscussionReseponseDto>> getBoardList(Pageable pageable) {// 페이징 변수

        Page<DiscussionReseponseDto> discussionBoardPage = queryRepository.findAllWithFetchJoin(pageable);

        if(discussionBoardPage.getTotalElements() == 0){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }else{
            return DefaultRes.response(HttpStatus.OK.value(), "조회성공", data(discussionBoardPage), new Pagination(discussionBoardPage));
        }
    }

    private List<DiscussionReseponseDto> data(Page<DiscussionReseponseDto> discussionBoardPage) {

        return discussionBoardPage.stream()
                .map(board ->
                        new DiscussionReseponseDto(board, readhitQueryRepository.findByDiscussionId(board.getId())))
                .collect(Collectors.toList());
    }

    // 게시글 상세 보기
    public DefaultRes<DiscussionDetailResponse> getDetailBoard(Long id) {   // 게시판 id

        Optional<DiscussionBoard> discussionBoard = queryRepository.findById(id);
        Optional<DiscussionBoardReadhit> readhit = readhitQueryRepository.findByDiscussionId(id);

        if(discussionBoard.isEmpty() && readhit.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }else {

            readhit.get().incrementCount();     // 조회수 카운트 증가

            readhitQueryRepository.updateCount(id, readhit.get().getCount());    // 상세페이지를 보게 되면 조회 수 증가 값을 update

            return discussionBoard
                    .map(board ->
                            DefaultRes.response(HttpStatus.OK.value(), "상세보기성공", new DiscussionDetailResponse(board)))
                    .orElseGet(() -> DefaultRes.response(HttpStatus.OK.value(), "상세보기 실패"));
        }
    }

    // 게시글 등록
    public DefaultRes insertBoard(DiscussionInsertRequestDto requestDto, Long userId) {  // 등록Dto

        Optional<User> user = userRepository.findById(userId);

        if(requestDto == null){
            return DefaultRes.response(HttpStatus.OK.value(), "등록실패");
        }else {
            DiscussionBoard board = discussionBoardRepository.save(requestDto.toEntity(requestDto, user));

            DiscussionBoardReadhit readhit = DiscussionBoardReadhit.builder()
                    .discussionBoard(board)
                    .count(0)
                    .build();

            readhitRepository.save(readhit);

            return DefaultRes.response(HttpStatus.OK.value(), "등록성공");
        }
    }

    // 게시글 수정
    public DefaultRes updateBoard(DiscussionEditRequest update, Long id, Long userId) { // 업데이트Dto, 게시글 번호, 유저 번호

        Optional<DiscussionBoard> optionalBoard =queryRepository.findById(id);

        if(optionalBoard.isEmpty()) {
            DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }

        return optionalBoard.filter(board -> board.getUser().getId().equals(userId))
                .filter(board -> board.getId().equals(id))
                .map(board -> {

                    queryRepository.updateBoard(update, id, userId);

                    return DefaultRes.response(HttpStatus.OK.value(), "수정성공");
                }).orElseGet(() -> DefaultRes.response(HttpStatus.OK.value(), "수정실패"));
    }

    // 게시글 삭제
    public DefaultRes delete(Long id, Long userId) { // 게시글 번호, 유저번호
        Optional<DiscussionBoard> optionalBoard = queryRepository.findById(id);

        if (optionalBoard.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }

        return optionalBoard.filter(board -> board.getId().equals(id))
                .filter(board -> board.getUser().getId().equals(userId))
                .map(board -> {
                    readhitQueryRepository.deleteByDiscussionId(board.getId());
                    replyQueryRepository.deleteByDiscussionId(board.getId());
                    queryRepository.deleteBoard(board.getId(), board.getUser().getId());

                    return DefaultRes.response(HttpStatus.OK.value(), "삭제성공");
                })
                .orElseGet(()-> DefaultRes.response(HttpStatus.OK.value(), "삭제실패"));

    }

    // 제목 검색
    public DefaultRes<List<DiscussionReseponseDto>> searchTitle(String title, Pageable pageable) {

        Page<DiscussionReseponseDto> boardPage = queryRepository.findByTitleContaining(title, pageable);

        if(boardPage.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }

        return DefaultRes.response(HttpStatus.OK.value(), "검색성공", searchTitleResult(boardPage), new Pagination(boardPage));
    }

    private List<DiscussionReseponseDto> searchTitleResult(Page<DiscussionReseponseDto> boardPage) {

        return boardPage.stream()
                .map(board -> new DiscussionReseponseDto(board, readhitQueryRepository.findByDiscussionId(board.getId())))
                .collect(Collectors.toList());
    }

    // 내용 검색
    public DefaultRes<List<DiscussionReseponseDto>> searchContent(String content, Pageable pageable) {

        Page<DiscussionReseponseDto> boardPage = queryRepository.findByContentContaining(content, pageable);

        if(boardPage.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }

        return DefaultRes.response(HttpStatus.OK.value(), "검색성공", searchContentResult(boardPage), new Pagination(boardPage));
    }

    private List<DiscussionReseponseDto> searchContentResult(Page<DiscussionReseponseDto> boardPage) {

        return boardPage.stream()
                .map(board -> new DiscussionReseponseDto(board, readhitQueryRepository.findByDiscussionId(board.getId())))
                .collect(Collectors.toList());
    }

    // 제목 + 내용 검색
    public DefaultRes<List<DiscussionReseponseDto>> searchAll(String search, Pageable pageable) {
        Page<DiscussionReseponseDto> boardPage = queryRepository.findByAllContaining(search, pageable);

        if(boardPage.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }

        return DefaultRes.response(HttpStatus.OK.value(), "검색성공", searchAllResult(boardPage), new Pagination(boardPage));
    }

    private List<DiscussionReseponseDto> searchAllResult(Page<DiscussionReseponseDto> boardPage) {

        return boardPage.stream()
                .map(board -> new DiscussionReseponseDto(board, readhitQueryRepository.findByDiscussionId(board.getId())))
                .collect(Collectors.toList());
    }
}