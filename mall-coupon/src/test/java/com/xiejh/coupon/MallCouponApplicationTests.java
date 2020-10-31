package com.xiejh.coupon;

import com.xiejh.coupon.entity.CouponEntity;
import com.xiejh.coupon.service.CouponService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class MallCouponApplicationTests {

    @Autowired
    private CouponService couponService;

    @Test
    void contextLoads() {
        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setAmount(BigDecimal.ONE);
        couponEntity.setCode("a");
        couponEntity.setCouponName("优惠卷");
        couponService.save(couponEntity);
        System.out.println("保存成功");
    }

}
