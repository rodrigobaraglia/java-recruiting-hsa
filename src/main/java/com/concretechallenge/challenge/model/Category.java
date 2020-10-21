package com.concretechallenge.challenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

public class Category {

    public String id;

    public String name;

    public int relevance;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String image;
   // @JsonInclude(JsonInclude.Include.NON_NULL)

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String smallImageUrl;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String iconImageUrl;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public List<Category> subcategories;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public List<String> subcategoriesIds;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public List<String> subcategoriesNames;



    public Category(String id, String name, int relevance, String image, String smallImageUrl, String iconImageUrl, List<Category> subcategories, List<String> subcategoriesNames, List<String> getSubcategoriesIds) {
        this.id = id;
        this.name = name;
        this.relevance = relevance;
        this.image = image;
        this.smallImageUrl = smallImageUrl;
        this.iconImageUrl = iconImageUrl;
        this.subcategories = subcategories;
        this.subcategoriesIds = getSubcategoriesIds;
        this.subcategoriesNames = subcategoriesNames;

    }

    public Category(Category c) {
        this.id = c.id;
        this.name = c.name;
        this.relevance = c.relevance;
        this.image = c.smallImageUrl == null ? c.iconImageUrl : c.smallImageUrl;
        this.subcategoriesIds = c.subcategories.stream().map(x -> x.id).collect(Collectors.toList());
        this.subcategoriesNames = c.subcategories.stream().map(x -> x.name).collect(Collectors.toList());
    }


    public List<Category> mapToFlatSorted() {
        return this.subcategories
                .get(0)
                .subcategories
                .stream()
                .map(x -> new Category(x))
                .sorted((x,y) -> y.relevance - x.relevance)
                .collect(Collectors.toList());
    }

    public List<Category> topFive() {
        return this.mapToFlatSorted().subList(0,5);
    }
    public List<Category> belowTopFive() {
        List<Category> l = this.mapToFlatSorted();
        return l.subList(5, l.size());
    }


}
