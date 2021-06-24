package udodog.goGetterServer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udodog.goGetterServer.model.entity.DiscussionBoard;

import java.util.List;

@Repository
public interface DiscussonBoardRepository extends JpaRepository<DiscussionBoard, Long> {

    Page<DiscussionBoard> findAll(Pageable pageable);
//    List<DiscussionBoard> findByAll();

}
