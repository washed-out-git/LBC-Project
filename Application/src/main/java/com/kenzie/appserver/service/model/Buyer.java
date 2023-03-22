package com.kenzie.appserver.service.model;

import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;

public class Buyer extends User {

    private final String userId;
    private String buyerName;
    public List<Bid> bidList;

    public Buyer(String userId, String buyerName, List<Bid> bidList) {
        this.buyerName = buyerName;
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

    public List<Bid> getBidList() {
        return bidList;
    }

    public void setBidList(List<Bid> bidList) {
        this.bidList = bidList;
    }
}
