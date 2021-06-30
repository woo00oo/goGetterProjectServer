package udodog.goGetterServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import udodog.goGetterServer.model.entity.DiscussionBoardReadhit;

import java.util.Optional;

public interface DiscussionBoardReadhitRepository extends JpaRepository<DiscussionBoardReadhit,Long> {

    @Transactional
    @Modifying
    @Query(value = "update DiscussionBoardReadhit rh set rh.count = :count where rh.discussionBoard.id = :discussionId")
    void updateCount(@Param("discussionId") Long discussionId, Integer count);

    @Query(value = "select rh from DiscussionBoardReadhit rh where rh.discussionBoard.id = :discussionId")
    Optional<DiscussionBoardReadhit> findByDiscussionId(Long discussionId);

    @Transactional
    @Modifying
    @Query(value = "delete from DiscussionBoardReadhit rh where rh.discussionBoard.id = :discussionId")
    void deleteByDiscussionId(Long discussionId);
}
