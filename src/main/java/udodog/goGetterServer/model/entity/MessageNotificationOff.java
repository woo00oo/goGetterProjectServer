package udodog.goGetterServer.model.entity;

import lombok.*;
import javax.persistence.*;

@Entity @Builder
@EqualsAndHashCode(of = "id")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageNotificationOff {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User sender;

    @OneToOne
    private User receiver;

}
