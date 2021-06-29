package udodog.goGetterServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import udodog.goGetterServer.model.entity.DiscussionBoardReadhit;

public interface DiscussionBoardReadhitRepository extends JpaRepository<DiscussionBoardReadhit,Long> {

}
