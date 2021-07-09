package udodog.goGetterServer.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter @NoArgsConstructor
public class Message {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private User receiver;

    private String content;

    @CreatedDate
    private LocalDateTime sendAt;

    private Boolean isChecked;

    private Boolean isDeleted;

    @Builder
    public Message(User receiver, User sender, String content, LocalDateTime sendAt) {
        this.receiver = receiver;
        this.sender = sender;
        this.content = content;
        this.sendAt = sendAt;
        this.isChecked = false;
        this.isDeleted = false;
    }


    public void checkMessage(){
        this.isChecked = true;
    }

    public void deleteMessage(){
        this.isDeleted = true;
    }
}
