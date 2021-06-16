package udodog.goGetterServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udodog.goGetterServer.model.entity.SharingBoardLike;

@Repository
public interface LikeRepository extends JpaRepository<SharingBoardLike,Long> {
}
