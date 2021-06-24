package udodog.goGetterServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udodog.goGetterServer.model.entity.SharingBoard;

@Repository
public interface SharingBoardRepository extends JpaRepository<SharingBoard,Long> {
}
