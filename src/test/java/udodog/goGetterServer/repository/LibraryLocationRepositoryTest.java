package udodog.goGetterServer.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import udodog.goGetterServer.model.entity.LibraryLocation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LibraryLocationRepositoryTest {

    @Autowired
    private LibraryLocationRepository libraryLocationRepository;

    @Test
    void 도서관_위치_저장() {

          // given → 테스트를 준비하는 과정 (~가 주어지고)
        LibraryLocation libraryLocation = LibraryLocation.builder()
            .libraryLocationCity("서울특별시")
            .libraryLocationGu("강남구")
            .libraryLocationDong("강남3동")
            .libraryLocationStreetNumber("32번길")
            .libraryPhoneNumber("02-492-4894")
            .build();

          // when → 실제로 테스트를 실행하는 과정 (~을 했을 때)
        LibraryLocation saveLibraryLocation = libraryLocationRepository.save(libraryLocation);

          // then → 테스트를 검증하는 과정 (~한 값이 나와야 함.)
        assertThat(libraryLocation).isEqualTo(saveLibraryLocation);

    } // Method 끝

}