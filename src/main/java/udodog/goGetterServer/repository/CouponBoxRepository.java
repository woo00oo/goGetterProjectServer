package udodog.goGetterServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udodog.goGetterServer.model.entity.CouponBox;

public interface CouponBoxRepository extends JpaRepository<CouponBox, Long> {
}
