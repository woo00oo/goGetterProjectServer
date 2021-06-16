package udodog.goGetterServer.model.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;


@Entity @Builder
@EqualsAndHashCode(of = "id")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Message {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User receiver;

    @OneToOne
    private User sender;

    private String title;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private String content;

    private boolean isChecked;

    @CreatedDate
    private LocalDate sendAt;

    private boolean isDeleted;

}
