package udodog.goGetterServer.repository.querydsl.sharingboard;

import com.querydsl.jpa.JPQLQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import udodog.goGetterServer.model.entity.QSharingBoard;
import udodog.goGetterServer.model.entity.SharingBoard;
import udodog.goGetterServer.repository.SharingBoardLikeRepository;

import java.time.LocalDate;
import java.util.List;

public class SharingBoardExtensionImpl  extends QuerydslRepositorySupport implements SharingBoardExtension {

    public SharingBoardExtensionImpl() {
        super(SharingBoard.class);
    }

    @Override
    public List<SharingBoard> getWeeklyBest() {
        QSharingBoard sharingBoard = QSharingBoard.sharingBoard;

        LocalDate endTime = LocalDate.now().plusDays(7L);
        JPQLQuery<SharingBoard> query = from(sharingBoard).
                innerJoin(sharingBoard.user).
                fetchJoin().
                where(sharingBoard.createdAt.between(LocalDate.now(), endTime)).
                orderBy(sharingBoard.likeCnt.desc())
                .limit(3);

        // 좋아요 순으로 정렬하기 위해 sharingBoard Entity에 likeCnt 필드 추가

        return query.fetch();
    }
}
