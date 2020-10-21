package com.concretechallenge.challenge.service;
import com.concretechallenge.challenge.client.CategoriesClient;
import com.concretechallenge.challenge.model.Category;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CategoryService {


    @Autowired
    private ObjectProvider<CategoriesClient> categoriesClient;



    public List<Category> fetchCategory(boolean isTopFive) throws Exception {
        System.out.println("Preparing to fetch");
        try {
            Category c = categoriesClient.getObject().getData();
            return isTopFive ? c.topFive() : c.belowTopFive();
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception(e);
        }
    }



}