package com.concretechallenge.challenge.service;

import com.concretechallenge.challenge.model.Coupon;
import com.concretechallenge.challenge.client.CouponsClient;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CouponService {

    @Autowired
    private ObjectProvider<CouponsClient> couponsClients;


    public List<Coupon> fetchCoupons() throws Exception {

        try {
            System.out.println("Preparing to fetch");
            return filterExpired(couponsClients.getObject().getData());
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception(e);
        }

    }

    private List<Coupon> filterExpired(List<Coupon> coupons) throws Exception {
        if (coupons != null) {
            return coupons.stream()
                    .map(x -> {
                        x.setExpired();
                        return x;
                    })
                    .filter(x -> !x.isExpired())
                    .collect(Collectors.toList());

        }
        throw new Exception();
    }

}
