package udodog.goGetterServer.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import udodog.goGetterServer.model.entity.BookReportTag;

import java.util.List;
import java.util.Optional;

public interface BookReportTagRepository extends JpaRepository<BookReportTag, Long> {
    @Query(value = "select t from BookReportTag t join fetch t.bookReport where t.bookReport.bookReportId = :bookReportId")
    String findByBookReportId(@Param("bookReportId") Long bookReportId);

} // Class 끝
