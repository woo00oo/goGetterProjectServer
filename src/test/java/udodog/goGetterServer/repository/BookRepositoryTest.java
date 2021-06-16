package udodog.goGetterServer.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import udodog.goGetterServer.model.entity.Book;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
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
        assertThat(book).isEqualTo(saveBook);
        
    }
}
