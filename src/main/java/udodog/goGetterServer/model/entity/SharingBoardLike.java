package udodog.goGetterServer.model.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter @NoArgsConstructor
public class SharingBoardLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private SharingBoard sharingBoard;

    @Builder
    public SharingBoardLike(User user, SharingBoard sharingBoard) {
        this.user = user;
        this.sharingBoard = sharingBoard;
    }
}
