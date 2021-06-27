package udodog.goGetterServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udodog.goGetterServer.model.entity.SharingBoardLike;

import java.util.Optional;

@Repository
public interface SharingBoardLikeRepository extends JpaRepository<SharingBoardLike,Long> {

    Integer countBySharingBoardId(Long id);

    Optional<SharingBoardLike> findByUserId(Long userId);
}
