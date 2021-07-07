package udodog.goGetterServer.model.dto.response.sharingboard;

import lombok.Getter;
import lombok.NoArgsConstructor;
import udodog.goGetterServer.model.entity.SharingBoard;
import udodog.goGetterServer.model.entity.SharingBoardReply;
import udodog.goGetterServer.model.entity.SharingBoardTag;

import java.time.LocalDate;
import java.util.LinkedList;
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

    private List<SharingReplyResponse> sharingBoardReplyList = new LinkedList<>();

    // 책 제목
    private String bookTitle;

    private List<String> sharingBoardTagList = new LinkedList<>();

    public BoardResponse(Optional<SharingBoard> sharingBoard, Integer replyCnt, Integer likeCnt, WriterInfo writerInfo, List<SharingBoardTag> sharingBoardTagList) {
        SharingBoard board = sharingBoard.get();

        this.id = board.getId();
        this.writerInfo = writerInfo;
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createdAt = board.getCreatedAt();
        this.bookTitle = board.getBookTitle();

        this.replyCnt = replyCnt;
        this.likeCnt = likeCnt;

        for (SharingBoardReply sharingBoardReply : board.getSharingBoardReplyList()){
            SharingReplyResponse sharingReplyResponse = new SharingReplyResponse(sharingBoardReply, writerInfo);
            this.sharingBoardReplyList.add(sharingReplyResponse);
        }

        if (!sharingBoardTagList.isEmpty()) {
            sharingBoardTagList.stream().forEach(tag -> this.sharingBoardTagList.add(tag.getContent()));
        }

    }
}
