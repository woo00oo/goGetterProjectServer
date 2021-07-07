package udodog.goGetterServer.service.coupon;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.coupon.CouponCreateRequestDto;
import udodog.goGetterServer.repository.CouponRepository;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    public DefaultRes couponCreate(CouponCreateRequestDto request){

        couponRepository.save(request.toEntity());

        return DefaultRes.response(HttpStatus.OK.value(), "등록성공");

    }
}
