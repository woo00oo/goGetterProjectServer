package udodog.goGetterServer.model.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import udodog.goGetterServer.model.dto.request.sharingboard.UpdateBoardRequest;
import udodog.goGetterServer.model.dto.request.sharingboard.creatBoardRequest;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter @NoArgsConstructor
public class SharingBoard {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch =FetchType.LAZY)
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

    public SharingBoard(creatBoardRequest request, Optional<User> user) {
        this.user = user.get();
        this.title = request.getTitle();
        this.content = request.getContent();
    }

    public Integer getReplyCnt(){
        return this.sharingBoardReplyList.size();
    }

    public SharingBoard updateBoard(UpdateBoardRequest request) {

        this.title = request.getTitle();
        this.content = request.getContent();

        return this;
    }
}
