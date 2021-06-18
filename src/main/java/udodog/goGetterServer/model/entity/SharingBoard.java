package udodog.goGetterServer.model.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter @NoArgsConstructor
public class SharingBoard {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String title;

    private String content;

    @CreatedDate
    private LocalDate createdAt;

    @OneToMany(mappedBy = "sharingBoard" )
    private List<SharingBoardReply> sharingBoardReplyList = new LinkedList<>();

    @Builder
    public SharingBoard(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }
}
