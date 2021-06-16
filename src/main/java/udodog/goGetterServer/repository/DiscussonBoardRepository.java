package udodog.goGetterServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udodog.goGetterServer.model.entity.DiscussionBoard;


public interface DiscussonBoardRepository extends JpaRepository<DiscussionBoard, Long> {
}
