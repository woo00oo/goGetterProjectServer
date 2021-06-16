package udodog.goGetterServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udodog.goGetterServer.model.entity.BookReportTag;

public interface BookReportTagRepository extends JpaRepository<BookReportTag, Long> {
}
