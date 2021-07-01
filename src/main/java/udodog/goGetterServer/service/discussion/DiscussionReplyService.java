package udodog.goGetterServer.service.discussion;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.Pagination;
import udodog.goGetterServer.model.dto.request.discussion.DiscussionReplyEditRequest;
import udodog.goGetterServer.model.dto.request.discussion.DiscussionReplyInsertRequest;
import udodog.goGetterServer.model.dto.response.discussion.DiscussionReplyResponse;
import udodog.goGetterServer.model.entity.DiscussionBoard;
import udodog.goGetterServer.model.entity.DiscussionBoardReply;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.repository.DiscussionBoardReplyRepository;
import udodog.goGetterServer.repository.DiscussionBoardRepository;
import udodog.goGetterServer.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiscussionReplyService {

    private final DiscussionBoardReplyRepository replyRepository;
    private final DiscussionBoardRepository boardRepository;
    private final UserRepository userRepository;

    // 댓글 등록
    public DefaultRes createReply(DiscussionReplyInsertRequest requestDto, Long discussionId, Long userId) {

        if(requestDto == null) {
            return DefaultRes.response(HttpStatus.OK.value(), "등록실패");
        }else {

            Optional<DiscussionBoard> board = boardRepository.findById(discussionId);
            Optional<User> user = userRepository.findById(userId);

            replyRepository.save(requestDto.toEntity(requestDto, board, user));
            return DefaultRes.response(HttpStatus.OK.value(), "등록성공");
        }
    }

    // 댓글 조회
    public DefaultRes<List<DiscussionReplyResponse>> getBoardReplyList(Long discussionId, Pageable pageable) {

        Page<DiscussionBoardReply> replyResponsesPage = replyRepository.findAllWithFetchJoin(discussionId, pageable);

        if (replyResponsesPage.getTotalElements() == 0){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }else {
            return DefaultRes.response(HttpStatus.OK.value(), "조회성공", replyData(replyResponsesPage), new Pagination(replyResponsesPage));
        }
    }

    private List<DiscussionReplyResponse> replyData(Page<DiscussionBoardReply> replyResponsesPage) {
        return replyResponsesPage.stream()
                .map(DiscussionReplyResponse::new)
                .collect(Collectors.toList());
    }


    // 댓글 수정
    public DefaultRes updateReply(DiscussionReplyEditRequest requestDto, Long discussionId, Long userId) {

        Optional<DiscussionBoardReply> boardReply = replyRepository.findByDiscussionId(discussionId);

        if (boardReply.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }

        return boardReply.filter(reply -> reply.getDiscussionBoard().getId().equals(discussionId))
                .filter(reply -> reply.getUser().getId().equals(userId))
                .map(reply -> {
                        DiscussionBoardReply replyBoard = reply.updateReply(requestDto);
                        replyRepository.save(replyBoard);

                        return DefaultRes.response(HttpStatus.OK.value(), "수정성공");
                })
                .orElseGet(() -> DefaultRes.response(HttpStatus.OK.value(), "수정실패"));
    }

    // 댓글 삭제
    public DefaultRes delete(Long id, Long userId) {
        Optional<DiscussionBoardReply> deleteReply = replyRepository.findById(id);

        if (deleteReply.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }

        return deleteReply.filter(replyboard -> replyboard.getId().equals(id))
                .filter(replyBoard -> replyBoard.getUser().getId().equals(userId))
                .map(replyBoard -> {
                    replyRepository.delete(id, userId);
                    return DefaultRes.response(HttpStatus.OK.value(), "삭제성공");
                }).orElseGet(() -> DefaultRes.response(HttpStatus.OK.value(), "삭제실패"));
    }
}
