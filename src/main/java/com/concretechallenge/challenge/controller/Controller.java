package com.concretechallenge.challenge.controller;

import com.concretechallenge.challenge.model.Category;
import com.concretechallenge.challenge.model.Coupon;
import com.concretechallenge.challenge.service.CategoryService;
import com.concretechallenge.challenge.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {

    @Autowired
    ObjectProvider<CategoryService> category;
    @Autowired
    ObjectProvider<CouponService> coupons;



    @GetMapping("/categories")
    public List<Category> getCategories
            (@RequestParam(defaultValue = "true")boolean isTopFive) throws Exception {
        try {
            return category.getObject().fetchCategory(isTopFive);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @GetMapping("/coupons")
    public List<Coupon> getCoupons() throws Exception {
        int tries = 0;
        int limit = 3;
        while(true) {
            try {
                return coupons.getObject().fetchCoupons();

            } catch (Exception e) {
                tries ++;
                if (tries == limit) throw new Exception(e);
            }
        }



    }

}