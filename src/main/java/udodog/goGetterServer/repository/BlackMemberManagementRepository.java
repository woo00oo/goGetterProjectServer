package udodog.goGetterServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udodog.goGetterServer.model.entity.BlackMemberManagement;

public interface BlackMemberManagementRepository extends JpaRepository<BlackMemberManagement, Long> {
}
