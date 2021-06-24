package udodog.goGetterServer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import udodog.goGetterServer.model.entity.BlackMemberManagement;

public interface BlackMemberManagementRepository extends JpaRepository<BlackMemberManagement, Long> {

<<<<<<< Updated upstream
    Page<BlackMemberManagement> findAll(Pageable pageable);
=======
    Page<BlackMemberManagement> findByAll(Pageable pageable);
>>>>>>> Stashed changes
} // Class 끝
