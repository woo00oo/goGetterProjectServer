package udodog.goGetterServer.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import udodog.goGetterServer.model.entity.AdvertisementManagement;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AdvertisementManagementRepositoryTest {

    @Autowired
    private AdvertisementManagementRepository advertisementManagementRepository;

    @Test
    void 광고_저장() {

          // given → 테스트를 준비하는 과정 (~가 주어지고)
        AdvertisementManagement advertisementManagement = AdvertisementManagement.builder()
            .name("Naver")
            .image("image")
            .url("www.naver.com")
            .content("Naver")
            .build();

          // when → 실제로 테스트를 실행하는 과정 (~을 했을 때)
        AdvertisementManagement saveAdvertisementManagement = advertisementManagementRepository.save(advertisementManagement);

          // then → 테스트를 검증하는 과정 (~한 값이 나와야 함.)
        assertThat(advertisementManagement).isEqualTo(saveAdvertisementManagement);

    } // Method 끝
} // Class End