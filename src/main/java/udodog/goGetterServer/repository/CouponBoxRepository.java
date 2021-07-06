package udodog.goGetterServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udodog.goGetterServer.model.entity.CouponUseHistory;

public interface CouponBoxRepository extends JpaRepository<CouponUseHistory, Long> {
}
