package udodog.goGetterServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import udodog.goGetterServer.model.entity.SharingBoardReply;

@Repository
public interface SharingBoardReplyRepository extends JpaRepository<SharingBoardReply,Long> {
    void deleteBySharingBoardId(Long id);
}
