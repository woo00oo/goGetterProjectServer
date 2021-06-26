package udodog.goGetterServer.service.sharingboard;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.Pagination;
import udodog.goGetterServer.model.dto.request.sharingboard.UpdateBoardRequest;
import udodog.goGetterServer.model.dto.request.sharingboard.creatBoardRequest;
import udodog.goGetterServer.model.dto.response.sharingboard.BoardResponse;
import udodog.goGetterServer.model.dto.response.sharingboard.SimpleBoardResponse;
import udodog.goGetterServer.model.entity.SharingBoard;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.repository.SharingBoardLikeRepository;
import udodog.goGetterServer.repository.SharingBoardReplyRepository;
import udodog.goGetterServer.repository.SharingBoardRepository;
import udodog.goGetterServer.repository.UserRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SharingBoardService {

    private final SharingBoardRepository sharingBoardRepository;
    private final SharingBoardLikeRepository sharingBoardLikeRepository;
    private final SharingBoardReplyRepository sharingBoardReplyRepository;
    private final UserRepository userRepository;

    public DefaultRes<List<SimpleBoardResponse>> getBoardList(Pageable pageable) {
        Page<SharingBoard> sharingBoardList = sharingBoardRepository.findAll(pageable);

        if (sharingBoardList.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(),"데이터 없음");
        }

        return DefaultRes.response(HttpStatus.OK.value(),"조회 성공",getSimpleBoardResponseList(sharingBoardList),new Pagination(sharingBoardList));
    }

        public DefaultRes<BoardResponse> getBoardDetail(Long boardId) {
        Optional<SharingBoard> sharingBoard = sharingBoardRepository.findById(boardId);

        return sharingBoard.map(board -> DefaultRes.response(HttpStatus.OK.value(), "조회 성공",new BoardResponse(sharingBoard)))
                .orElseGet(()->{
                    return DefaultRes.response(HttpStatus.OK.
                            value(), "데이터 없음");
                });
    }

    public DefaultRes createSharingBoard(creatBoardRequest request) {
        Optional<User> user = userRepository.findById(request.getUserId());
        if (user.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(),"글 등록 실패");
        }

        SharingBoard sharingBoard = new SharingBoard(request, user);
        SharingBoard saveBoard = sharingBoardRepository.save(sharingBoard);

        if(sharingBoard.getId().equals(saveBoard.getId())){
            return DefaultRes.response(HttpStatus.OK.value(),"글 등록 성공");
        }

        return DefaultRes.response(HttpStatus.OK.value(),"글 등록 실패");

    }

    public DefaultRes<BoardResponse> updateSharingBoard(Long boardId, UpdateBoardRequest request) {
        Optional<SharingBoard> boardById = sharingBoardRepository.findById(boardId);

        if (boardById.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(),"글이 존재하지 않음");
        }

        if(!isWriter(request, boardById)){
            return DefaultRes.response(HttpStatus.OK.value(),"글 수정 실패");
        }

        // UPDATE
        SharingBoard updateBoard = boardById.get().updateBoard(request);
        SharingBoard saveBoard = sharingBoardRepository.save(updateBoard);
        //

        if(saveBoard.getId().equals(boardId)){
            return DefaultRes.response(HttpStatus.OK.value(),"글 수정 성공");
        }

        return DefaultRes.response(HttpStatus.OK.value(),"글 수정 실패");
    }

    private boolean isWriter(UpdateBoardRequest request, Optional<SharingBoard> boardById) {
        return boardById.get().getUser().getId().equals(request.getUserId());
    }


    @Transactional
    public DefaultRes deleteSharingBoard(Long boardId, Long UserId) {

        Optional<SharingBoard> boardById = sharingBoardRepository.findById(boardId);

        if (boardById.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(),"글이 존재하지 않음");
        }

        if(!boardById.get().getUser().getId().equals(UserId)){
            return DefaultRes.response(HttpStatus.OK.value(),"글 삭제 실패");
        }

        sharingBoardReplyRepository.deleteBySharingBoardId(boardById.get().getId());
        sharingBoardRepository.deleteById(boardId);

        return DefaultRes.response(HttpStatus.OK.value(),"글 삭제 성공");

    }

    @NotNull
    private List<SimpleBoardResponse> getSimpleBoardResponseList(Page<SharingBoard> sharingBoardList) {
        List<SimpleBoardResponse> simpleBoardResponseList = new LinkedList<>();

        for(SharingBoard sharingBoard : sharingBoardList){
            Integer replyCnt = sharingBoard.getReplyCnt();
            Integer likeCnt = sharingBoardLikeRepository.countBySharingBoardId(sharingBoard.getId());

            SimpleBoardResponse simpleBoardResponse = new SimpleBoardResponse(sharingBoard, replyCnt, likeCnt);
            simpleBoardResponseList.add(simpleBoardResponse);
        }
        return simpleBoardResponseList;
    }


}
