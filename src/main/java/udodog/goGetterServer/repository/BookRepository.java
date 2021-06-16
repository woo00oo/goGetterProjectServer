package udodog.goGetterServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udodog.goGetterServer.model.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
