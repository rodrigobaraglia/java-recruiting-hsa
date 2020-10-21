package com.concretechallenge.challenge.client;


import com.concretechallenge.challenge.model.Coupon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="coupons-client", url="https://cs-hsa-api-coupons.herokuapp.com/")
public interface CouponsClient {
    @GetMapping("/coupons")
    List<Coupon> getData();
}

