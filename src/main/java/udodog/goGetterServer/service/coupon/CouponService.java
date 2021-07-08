package udodog.goGetterServer.service.coupon;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.coupon.CouponCreateRequestDto;
import udodog.goGetterServer.model.entity.Coupon;
import udodog.goGetterServer.model.entity.CouponUseHistory;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.repository.CouponRepository;
import udodog.goGetterServer.repository.CouponUseHistoryRepository;
import udodog.goGetterServer.repository.UserRepository;
import udodog.goGetterServer.repository.querydsl.CouponQueryRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final UserRepository userRepository;

    private final CouponRepository couponRepository;

    private final CouponUseHistoryRepository couponUseHistoryRepository;

    private final CouponQueryRepository couponQueryRepository;

    public DefaultRes couponCreate(CouponCreateRequestDto request){

        couponRepository.save(request.toEntity());

        return DefaultRes.response(HttpStatus.OK.value(), "등록성공");

    }

    @Transactional
    public DefaultRes couponDownload(Long userId, Long couponId){

        Boolean overlapcheck = couponQueryRepository.overlapCoupon(userId, couponId);

        if(overlapcheck){
            Optional<Coupon> optionalCoupon = couponRepository.findById(couponId);
            Optional<User> optionalUser = userRepository.findById(userId);

            if(optionalCoupon.isPresent() && optionalUser.isPresent()){

                if(optionalCoupon.get().getQuantity() != 0){

                    LocalDate endDate = LocalDate.now().plusDays(optionalCoupon.get().getValidDate());
                    String serialNumber = "";

                    for (int i = 0; i < 20; i++){
                        serialNumber += Integer.toString(new Random().nextInt(10));
                    }

                    couponUseHistoryRepository.save(new CouponUseHistory(optionalUser.get(), optionalCoupon.get(), serialNumber, endDate));
                    optionalCoupon.get().decreaseQuantity();
                    return DefaultRes.response(HttpStatus.OK.value(), "다운로드성공");

                }else{
                    return DefaultRes.response(HttpStatus.OK.value(), "실패(수량0)");
                }

            }else {
                return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");
            }

        }else{
            return DefaultRes.response(HttpStatus.OK.value(), "실패(중복)");
        }
    }
}
