package udodog.goGetterServer.model.entity;

import lombok.*;
import javax.persistence.*;

@Entity @Builder
@EqualsAndHashCode(of = "id")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class SharingBoardLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private SharingBoard sharingBoard;

}
