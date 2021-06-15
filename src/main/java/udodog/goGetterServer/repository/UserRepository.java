package udodog.goGetterServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udodog.goGetterServer.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
