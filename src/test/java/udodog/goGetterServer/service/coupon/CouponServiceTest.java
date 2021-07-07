package udodog.goGetterServer.service.coupon;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.coupon.CouponCreateRequestDto;
import udodog.goGetterServer.model.entity.Coupon;
import udodog.goGetterServer.repository.CouponRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class CouponServiceTest {

    @InjectMocks
    private CouponService couponService;

    @Mock
    private CouponRepository couponRepository;

    @Test
    public void 쿠폰_등록(){
        //given
        String name = "쿠폰";
        Integer discount = 2000;
        Integer validDate = 30;
        Integer quantity = 100;

        Coupon coupon = Coupon.builder()
                .name(name)
                .discount(discount)
                .validDate(validDate)
                .quantity(quantity)
                .build();

        CouponCreateRequestDto request = new CouponCreateRequestDto(name, discount, validDate, quantity);

        given(couponRepository.save(any())).willReturn(coupon);

        //when
        DefaultRes defaultRes = couponService.couponCreate(request);

        //then

        assertThat(defaultRes.getMessage()).isEqualTo("등록성공");

    }


}