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

    private List<SharingBoardReply> sharingBoardReplyList;

    public BoardResponse(Optional<SharingBoard> sharingBoard) {
        SharingBoard board = sharingBoard.get();

        this.id = board.getId();
        WriterInfo writerInfo = WriterInfo.
                builder().
                nickName(board.getUser().getNickName()).
                profileUrl(board.getUser().getProfileUrl()).
                build();

        this.writerInfo = writerInfo;
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createdAt = board.getCreatedAt();
        this.sharingBoardReplyList = board.getSharingBoardReplyList();

    }
}
