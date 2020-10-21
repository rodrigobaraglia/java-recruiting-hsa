package com.concretechallenge.challenge.client;

import com.concretechallenge.challenge.model.Category;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "categories-client", url = "https://cs-hsa-api-categories.herokuapp.com")
public interface CategoriesClient {
    @GetMapping("/categories")
    Category getData();
}
