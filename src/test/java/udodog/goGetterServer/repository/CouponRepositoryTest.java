package udodog.goGetterServer.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import udodog.goGetterServer.model.entity.Coupon;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CouponRepositoryTest {

    @Autowired
    private CouponRepository couponRepository;

    @Test
    void 쿠폰_저장(){

        //given
        Coupon coupon = Coupon.builder()
                .name("문화상품권")
                .serialNumber(1234512345L)
                .validDate(30)
                .build();

        //when
        Coupon saveCoupon = couponRepository.save(coupon);

        //then
        Assertions.assertThat(coupon).isEqualTo(saveCoupon);
    }
}