package com.kenzie.appserver.service.model;

import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;

public class Buyer {

    private final String userId;
    private String buyerName;
    public List<String> bidList;

    public Buyer(String sellerName) {
        this.buyerName = sellerName;
        this.userId = randomUUID().toString();
        this.bidList = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public List<String> getBidList() {
        return bidList;
    }

    public void setBidList(List<String> bidList) {
        this.bidList = bidList;
    }
}
