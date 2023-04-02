package com.kenzie.appserver.service.model;
import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;

public class Buyer {

    private final String userId;
    private final String buyerName;
    public List<Bid> bidList;

    public Buyer(String buyerName) {
        this.buyerName = buyerName;
        this.userId = randomUUID().toString();
        this.bidList = new ArrayList<>();
    }
    public Buyer(String userId, String buyerName) {
        this.buyerName = buyerName;
        this.userId = userId;
    }
    public Buyer(String userId, String buyerName, List<Bid> bidList) {
        this.buyerName = buyerName;
        this.userId = userId;
        this.bidList = bidList;
    }

    public String getUserId() {
        return userId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public List<Bid> getBidList() {
        return bidList;
    }

    public void setBidList(List<Bid> bidList) {
        this.bidList = bidList;
    }
}
