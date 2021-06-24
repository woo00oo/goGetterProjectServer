package udodog.goGetterServer.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import udodog.goGetterServer.config.JpaAuditingConfig;
import udodog.goGetterServer.model.entity.Book;
import udodog.goGetterServer.model.entity.BookReport;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.model.enumclass.UserGrade;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = JpaAuditingConfig.class
))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookReportRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookReportRepository bookReportRepository;

    @Test
    void 독서기록_저장(){
        //given
        Book book = Book.builder()
                .bookName("토비의 스프링")
                .author("토비")
                .genre("기술서적")
                .price(3000)
                .build();

        User user = User.builder()
                .email("hwoo00oo96@gmail.com")
                .password("1234")
                .name("변현우")
                .nickName("woo00oo")
                .phoneNumber("010-9245-7396")
                .grade(UserGrade.USER)
                .build();

        Book saveBook = bookRepository.save(book);
        User saveUser = userRepository.save(user);

        BookReport bookReport = BookReport.builder()
                .book(saveBook)
                .user(saveUser)
                .title("공부 1일차")
                .content("독서 기록입니다")
                .build();

        //when
        BookReport saveBookReport = bookReportRepository.save(bookReport);

        //then
        assertThat(bookReport).isEqualTo(saveBookReport);


    }

}
