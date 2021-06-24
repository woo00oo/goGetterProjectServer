package udodog.goGetterServer.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import udodog.goGetterServer.config.JpaAuditingConfig;
import udodog.goGetterServer.model.entity.Book;

@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = JpaAuditingConfig.class
))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void 책_저장(){

        //given
        Book book = Book.builder()
                .bookName("토비의 스프링")
                .author("토비")
                .genre("기술서적")
                .price(3000)
                .build();
        
        //when
        Book saveBook = bookRepository.save(book);
        
        //then
        Assertions.assertThat(book).isEqualTo(saveBook);

    }
}
