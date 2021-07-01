package udodog.goGetterServer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import udodog.goGetterServer.model.entity.SharingBoard;
import udodog.goGetterServer.model.entity.SharingBoardReply;

import java.util.List;
import java.util.Optional;

@Repository
public interface SharingBoardRepository extends JpaRepository<SharingBoard,Long> {

    @Query(value = "select board from SharingBoard board join fetch board.user",
            countQuery = "select count(board) from SharingBoard ")
    Page<SharingBoard> findAll(Pageable pageable);



    @Query(value = "select board from SharingBoard board join fetch board.user where board.id = :boardId")
    Optional<SharingBoard> findById(@Param("boardId") Long id);



}
