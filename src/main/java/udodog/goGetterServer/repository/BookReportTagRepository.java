package udodog.goGetterServer.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udodog.goGetterServer.model.entity.BookReportTag;

public interface BookReportTagRepository extends JpaRepository<BookReportTag, Long> {
}
