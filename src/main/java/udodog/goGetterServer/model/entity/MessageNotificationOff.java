package udodog.goGetterServer.model.entity;

import lombok.*;
import javax.persistence.*;

@Entity @Getter
@NoArgsConstructor
public class MessageNotificationOff {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User sender;

    @OneToOne(fetch =  FetchType.LAZY)
    private User receiver;

    @Builder
    public MessageNotificationOff(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }
}
