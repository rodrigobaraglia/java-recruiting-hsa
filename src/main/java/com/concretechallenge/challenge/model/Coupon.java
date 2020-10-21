package com.concretechallenge.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Coupon {
    /*id	string
    description	string
    seller	string
    image	string
    expiresAt	string*/
    private String id;
    private String description;
    private String seller;
    private String image;
    private String expiresAt;
    private boolean expired;


    public boolean isExpired() {
        return expired;

    }

    public void setExpired() {
        Date today = new Date();
        try {
            Date expirationDate = new SimpleDateFormat("yyyy-mm-dd").parse(this.expiresAt);
            this.expired = today.after(expirationDate);
        } catch (Exception e) {
            System.out.println("Failed to parse date " + e);
        }
    }

    public Coupon(String id, String description, String seller, String image, String expiresAt) {
        this.id = id;
        this.description = description;
        this.seller = seller;
        this.image = image;
        this.expiresAt = expiresAt;
        this.setExpired();
    }

    public Coupon() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

}
