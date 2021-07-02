package udodog.goGetterServer.repository.querydsl;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import udodog.goGetterServer.model.entity.DiscussionBoardReadhit;

import javax.persistence.EntityManager;
import java.util.Optional;

import static udodog.goGetterServer.model.entity.QDiscussionBoardReadhit.discussionBoardReadhit;

@RequiredArgsConstructor
@Repository
public class DiscussionBoardReadhitQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    public Optional<DiscussionBoardReadhit> findByDiscussionId(Long discussionId){

        DiscussionBoardReadhit readhit =
                queryFactory
                        .selectFrom(discussionBoardReadhit)
                        .where(discussionBoardReadhit.discussionBoard.id.eq(discussionId))
                        .fetchOne();

        return Optional.ofNullable(readhit);
    }

    @Transactional
    public void updateCount(Long discussionId, Integer count){
        JPAUpdateClause updateClause = new JPAUpdateClause(em, discussionBoardReadhit);

        updateClause
                .where(discussionBoardReadhit.discussionBoard.id.eq(discussionId))
                .set(discussionBoardReadhit.count, count)
                .execute();
    }

    @Transactional
    public void deleteByDiscussionId(Long discussionId) {

        JPADeleteClause deleteClause = new JPADeleteClause(em, discussionBoardReadhit);

        deleteClause
                .where(discussionBoardReadhit.discussionBoard.id.eq(discussionId))
                .execute();
    }
}
