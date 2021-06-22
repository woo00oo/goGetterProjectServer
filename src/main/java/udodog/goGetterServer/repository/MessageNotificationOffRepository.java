package udodog.goGetterServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udodog.goGetterServer.model.entity.MessageNotificationOff;

@Repository
public interface MessageNotificationOffRepository extends JpaRepository<MessageNotificationOff,Long> {
}
