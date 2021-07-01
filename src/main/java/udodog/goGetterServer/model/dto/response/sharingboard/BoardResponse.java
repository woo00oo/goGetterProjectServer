package udodog.goGetterServer.model.dto.response.sharingboard;

import lombok.Getter;
import lombok.NoArgsConstructor;
import udodog.goGetterServer.model.entity.SharingBoard;
import udodog.goGetterServer.model.entity.SharingBoardReply;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Getter
public class BoardResponse {

    private Long id;

    private WriterInfo writerInfo;

    private String title;

    private String content;

    private LocalDate createdAt;

    // 댓글 수
    private Integer replyCnt;

    // 좋아요 수
    private Integer likeCnt;


    private List<SharingBoardReply> sharingBoardReplyList;

    public BoardResponse(Optional<SharingBoard> sharingBoard,Integer replyCnt, Integer likeCnt, WriterInfo writerInfo) {
        SharingBoard board = sharingBoard.get();

        this.id = board.getId();
        this.writerInfo = writerInfo;
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createdAt = board.getCreatedAt();

        this.replyCnt = replyCnt;
        this.likeCnt = likeCnt;

    }
}
