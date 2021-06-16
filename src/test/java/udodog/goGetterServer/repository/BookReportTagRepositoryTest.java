package udodog.goGetterServer.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import udodog.goGetterServer.model.entity.Book;
import udodog.goGetterServer.model.entity.BookReport;
import udodog.goGetterServer.model.entity.BookReportTag;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.model.enumclass.UserGrade;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookReportTagRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookReportRepository bookReportRepository;

    @Autowired
    private BookReportTagRepository bookReportTagRepository;

    @Test
    void 테그_저장(){

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

        BookReport saveBookReport = bookReportRepository.save(bookReport);

        BookReportTag bookReportTag = BookReportTag.builder()
                .bookReport(saveBookReport)
                .tagName("스프링")
                .build();

        //when
        BookReportTag saveBookReportTag = bookReportTagRepository.save(bookReportTag);

        //then
        Assertions.assertThat(bookReportTag).isEqualTo(saveBookReportTag);

    }
}
