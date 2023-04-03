package com.kenzie.appserver.service.model;

import java.util.ArrayList;
import java.util.List;

public class Seller {

    private final String userId;
    private String sellerName;

    public Seller(String sellerEmail, String sellerName) {
        this.userId = sellerEmail;
        this.sellerName = sellerName;
    }

    public String getUserId() {
        return userId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

}
// credit Teresa Bowen
