package udodog.goGetterServer.controller.api.coupon;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.coupon.CouponCreateRequestDto;
import udodog.goGetterServer.service.coupon.CouponService;

import javax.validation.Valid;

@Api(tags = {"쿠폰 관련 API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CouponController {

    private final CouponService couponService;

    @ApiOperation(value = "쿠폰 등록 API",notes = "관리자가 쿠폰 등록시 사용되는 API 입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 등록성공")
    })
    @PostMapping("/admin/coupons")
    public ResponseEntity<DefaultRes> couponCreate(
            @Valid @RequestBody CouponCreateRequestDto request
            ){
        return new ResponseEntity<>(couponService.couponCreate(request), HttpStatus.OK);
    }


}
