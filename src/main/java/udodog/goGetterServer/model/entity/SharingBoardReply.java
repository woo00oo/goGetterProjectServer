package udodog.goGetterServer.model.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class SharingBoardReply {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private SharingBoard sharingBoard;

    @Basic(fetch = FetchType.EAGER)
    private String comment;

    @CreatedDate
    private LocalDate createdAt;

    @Builder
    public SharingBoardReply(User user, SharingBoard sharingBoard, String comment) {
        this.user = user;
        this.sharingBoard = sharingBoard;
        this.comment = comment;
    }
}
