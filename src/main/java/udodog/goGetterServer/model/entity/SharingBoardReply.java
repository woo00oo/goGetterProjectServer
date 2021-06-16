package udodog.goGetterServer.model.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.time.LocalDate;

@Entity @Builder
@Getter @Setter
@EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)

public class SharingBoardReply {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private SharingBoard sharingBoard;

    @Basic(fetch = FetchType.EAGER)
    private String comment;

    @CreatedDate
    private LocalDate createdAt;

}
