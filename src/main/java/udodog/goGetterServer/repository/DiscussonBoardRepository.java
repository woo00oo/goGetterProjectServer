package udodog.goGetterServer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udodog.goGetterServer.model.entity.DiscussionBoard;

import java.util.Optional;

@Repository
public interface DiscussonBoardRepository extends JpaRepository<DiscussionBoard, Long> {

    Page<DiscussionBoard> findAll(Pageable pageable);
    Optional<DiscussionBoard> findById(Long id);

}
