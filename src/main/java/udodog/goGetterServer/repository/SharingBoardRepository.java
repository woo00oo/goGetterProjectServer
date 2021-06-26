package udodog.goGetterServer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import udodog.goGetterServer.model.entity.SharingBoard;

@Repository
public interface SharingBoardRepository extends JpaRepository<SharingBoard,Long> {

    @Query(value = "select board from SharingBoard board join fetch board.user join fetch board.sharingBoardReplyList",
            countQuery = "select count(board) from SharingBoard ")
    Page<SharingBoard> findAll(Pageable pageable);


}
