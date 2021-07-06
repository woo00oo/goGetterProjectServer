package udodog.goGetterServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udodog.goGetterServer.model.entity.SharingBoardTag;

public interface SharingBoardTagRepository extends JpaRepository<SharingBoardTag, Long> {
}
