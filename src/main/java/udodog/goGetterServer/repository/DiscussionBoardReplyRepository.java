package udodog.goGetterServer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import udodog.goGetterServer.model.entity.DiscussionBoardReply;

import java.util.Optional;

public interface DiscussionBoardReplyRepository extends JpaRepository<DiscussionBoardReply, Long> {

    @Query(value = "select dr from DiscussionBoardReply dr join fetch dr.discussionBoard d join fetch d.user where d.id= :id",
            countQuery = "select count(dr) from DiscussionBoardReply")
    Page<DiscussionBoardReply> findAllWithFetchJoin(Long id, Pageable pageable);

    @Query(value = "select dr from DiscussionBoardReply dr join fetch dr.discussionBoard d where d.id = :discussionId")
    Optional<DiscussionBoardReply> findByDiscussionId(Long discussionId);

    Optional<DiscussionBoardReply> findById(Long id);

    @Transactional
    @Modifying
    @Query(value = "delete from DiscussionBoardReply dr where dr.discussionBoard.id = :discussionId")
    void deleteByDiscussionId(Long discussionId);

}
