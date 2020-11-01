package com.xiejh.user.feign;

import com.xiejh.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author xiejh
 * @Date 2020/10/31 11:49
 **/
@FeignClient("mall-coupon")
public interface CouponFeignService {

    @PostMapping("/coupon/coupon/getMemberCoupon")
    public R getMemberCoupon();
}
