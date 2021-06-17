package udodog.goGetterServer.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class BlackMemberManagement {

    @Id
    // 기본키 생성을 DB에 위임 시켜 준다. 즉, 자동 생성(시퀀스 넘버)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blackMemberMgtId;

    @OneToOne
    // FK에 관련 설정
    @JoinColumn(name = "user_id")
    private User userId;
    
    @Builder
    public BlackMemberManagement(Long blackMemberMgtId, User userId) {
        this.blackMemberMgtId = blackMemberMgtId;
        this.userId = userId;
    } // 생성자 끝
} // Class End
