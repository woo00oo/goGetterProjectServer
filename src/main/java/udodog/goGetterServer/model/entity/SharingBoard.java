package udodog.goGetterServer.model.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity @Builder
@EqualsAndHashCode(of = "id")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class SharingBoard {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String title;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private String content;

    @CreatedDate
    private LocalDate createdAt;

    @OneToMany(mappedBy = "sharingBoard" )
    private Set<SharingBoardReply> sharingBoardReplies;
}
