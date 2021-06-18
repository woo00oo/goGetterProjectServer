package udodog.goGetterServer.model.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter @NoArgsConstructor
public class Message {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User receiver;

    @OneToOne(fetch = FetchType.LAZY)
    private User sender;

    private String title;

    private String content;

    @CreatedDate
    private LocalDate sendAt;

    private boolean isChecked;

    private boolean isDeleted;

    @Builder
    public Message(User receiver, User sender, String title, String content) {
        this.receiver = receiver;
        this.sender = sender;
        this.title = title;
        this.content = content;
    }

    public void checkMessage(){
        this.isChecked = true;
    }

    public void deleteMessage(){
        this.isDeleted = true;
    }
}
