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
import udodog.goGetterServer.model.entity.DiscussionBoardReply;
import udodog.goGetterServer.repository.DiscussionBoardReplyRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiscussionReplyService {

    private final DiscussionBoardReplyRepository replyRepository;

    // 댓글 등록
    public DefaultRes createReply(DiscussionReplyInsertRequest requestDto) {

        if(requestDto == null) {
            return DefaultRes.response(HttpStatus.OK.value(), "등록실패");
        }else {
           replyRepository.save(requestDto.toEntity(requestDto));
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
    public DefaultRes updateReply(DiscussionReplyEditRequest requestDto, Long discussionId) {

        Optional<DiscussionBoardReply> boardReply = replyRepository.findByDiscussionId(discussionId);

        if (boardReply.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }

        if(!(boardReply.get().getUser().getId().equals(requestDto.getUserId()))){
            return DefaultRes.response(HttpStatus.OK.value(), "수정실패");
        }

        DiscussionBoardReply replyBoard = boardReply.get().updateReply(requestDto);
        DiscussionBoardReply saveReply  = replyRepository.save(replyBoard);

        if(boardReply.get().getDiscussionBoard().getId().equals(saveReply.getDiscussionBoard().getId())){
            return DefaultRes.response(HttpStatus.OK.value(), "수정성공");
        }else {
            return DefaultRes.response(HttpStatus.OK.value(), "수정실패");
        }
    }

    // 댓글 삭제
    public DefaultRes delete(Long id, Long userId) {
        Optional<DiscussionBoardReply> deleteReply = replyRepository.findById(id);

        if (deleteReply.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
        }

        if(!(deleteReply.get().getUser().getId().equals(userId))){
            return DefaultRes.response(HttpStatus.OK.value(), "삭제실패");
        }

        if(!(deleteReply.get().getId().equals(id))){
            return DefaultRes.response(HttpStatus.OK.value(), "삭제실패");
        }

        replyRepository.deleteById(id);
        return DefaultRes.response(HttpStatus.OK.value(), "삭제성공");
    }
}
