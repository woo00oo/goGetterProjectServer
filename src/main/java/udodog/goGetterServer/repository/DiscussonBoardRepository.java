package udodog.goGetterServer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import udodog.goGetterServer.model.entity.DiscussionBoard;

import java.util.Optional;

@Repository
public interface DiscussonBoardRepository extends JpaRepository<DiscussionBoard, Long> {

    @Query(value = "select d from DiscussionBoard d join fetch d.user",
            countQuery = "select count(d) from DiscussionBoard")
    Page<DiscussionBoard> findAllWithFetchJoin(Pageable pageable);

    @Query(value = "select d from DiscussionBoard d join fetch d.user where d.id= :id")
    Optional<DiscussionBoard> findById(Long id);

}
