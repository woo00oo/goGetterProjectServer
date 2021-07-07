package udodog.goGetterServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import udodog.goGetterServer.model.entity.Event;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e JOIN FETCH e.coupon WHERE e.id = :id")
    Optional<Event> findById(@Param("id") Long id);
}
