package udodog.goGetterServer.repository.querydsl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import udodog.goGetterServer.model.dto.response.bookreport.BookreportResponseDto;

import javax.persistence.EntityManager;
import java.util.List;

import static udodog.goGetterServer.model.entity.QBookReport.bookReport;
import static udodog.goGetterServer.model.entity.QUser.user;

@RequiredArgsConstructor
@Repository
public class BookReportQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    // 전체 조회
    public Page<BookreportResponseDto> findAllWithFetchJoin(Pageable pageable) { // 페이징 처리

        List<BookreportResponseDto> reportList = jpaQueryFactory.select(Projections.fields(BookreportResponseDto.class,
                bookReport.bookReportId,
                bookReport.user,
                bookReport.title,
                bookReport.createdAt))
           .from(bookReport)
           .innerJoin(bookReport.user, user)
           .fetch();

        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), reportList.size());

        return new PageImpl<>(reportList.subList(start, end), pageable, reportList.size());
    } // reportList끝

} // Class 끝
