package udodog.goGetterServer.repository.querydsl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import udodog.goGetterServer.model.dto.request.discussion.DiscussionReplyEditRequest;
import udodog.goGetterServer.model.dto.response.discussion.DiscussionReplyResponse;
import udodog.goGetterServer.model.entity.DiscussionBoardReply;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static udodog.goGetterServer.model.entity.QDiscussionBoardReply.discussionBoardReply;
import static udodog.goGetterServer.model.entity.QUser.user;

@RequiredArgsConstructor
@Repository
public class DiscussionBoardReplyQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    public Page<DiscussionReplyResponse> findAllWithFetchJoin(Long discussionId, Pageable pageable) {

        List<DiscussionReplyResponse> boardReplyList =
                queryFactory
                        .select(Projections.fields(DiscussionReplyResponse.class,
                                discussionBoardReply.id,
                                discussionBoardReply.user,
                                discussionBoardReply.content,
                                discussionBoardReply.createAt))
                        .from(discussionBoardReply)
                        .innerJoin(discussionBoardReply.user, user)
                        .where(discussionBoardReply.discussionBoard.id.eq(discussionId))
                        .fetch();

        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), boardReplyList.size());

        return new PageImpl<>(boardReplyList.subList(start, end), pageable, boardReplyList.size());
    }

    public Optional<DiscussionBoardReply> findById(Long replyId) {

        DiscussionBoardReply replyBoard =
                queryFactory
                        .selectFrom(discussionBoardReply)
                        .innerJoin(discussionBoardReply.user, user)
                        .fetchJoin()
                        .where(discussionBoardReply.id.eq(replyId))
                        .fetchOne();

        return Optional.ofNullable(replyBoard);
    }

    @Transactional
    public void updateBoard(DiscussionReplyEditRequest requestDto, Long replyId, Long userId) {

        JPAUpdateClause updateClause = new JPAUpdateClause(em, discussionBoardReply);

        updateClause
                .where(discussionBoardReply.id.eq(replyId), discussionBoardReply.user.id.eq(userId))
                .set(discussionBoardReply.content, requestDto.getContent())
                .execute();
    }

    @Transactional
    public void deleteById(Long replyId, Long userId) {

        JPADeleteClause deleteClause = new JPADeleteClause(em, discussionBoardReply);

        deleteClause
                .where(discussionBoardReply.id.eq(replyId), discussionBoardReply.user.id.eq(userId))
                .execute();
    }

    @Transactional
    public void deleteByDiscussionId(Long discussionId) {
        JPADeleteClause deleteClause = new JPADeleteClause(em, discussionBoardReply);

        deleteClause
                .where(discussionBoardReply.discussionBoard.id.eq(discussionId))
                .execute();
    }
}
