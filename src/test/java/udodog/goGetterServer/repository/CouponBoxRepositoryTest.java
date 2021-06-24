package udodog.goGetterServer.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import udodog.goGetterServer.config.JpaAuditingConfig;
import udodog.goGetterServer.model.entity.Coupon;
import udodog.goGetterServer.model.entity.CouponBox;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.model.enumclass.UserGrade;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = JpaAuditingConfig.class
))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CouponBoxRepositoryTest {

    @Autowired
    private CouponBoxRepository couponBoxRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void 쿠폰함_저장(){

        //given
        Coupon coupon = Coupon.builder()
                .name("문화상품권")
                .serialNumber(1234512345L)
                .validDate(30)
                .build();

        User user = User.builder()
                .email("hwoo00oo96@gmail.com")
                .password("1234")
                .name("변현우")
                .nickName("woo00oo")
                .phoneNumber("010-9245-7396")
                .grade(UserGrade.USER)
                .build();


        User saveUser = userRepository.save(user);
        Coupon saveCoupon = couponRepository.save(coupon);

        CouponBox couponBox = CouponBox.builder()
                .user(saveUser)
                .coupon(saveCoupon)
                .endDate(LocalDate.of(2021,07,15))
                .build();

        //when
        CouponBox saveCouponBox = couponBoxRepository.save(couponBox);

        //then

        assertThat(couponBox).isEqualTo(saveCouponBox);
    }
}
