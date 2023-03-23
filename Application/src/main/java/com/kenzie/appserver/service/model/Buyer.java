package com.kenzie.appserver.service.model;

<<<<<<< HEAD
import com.kenzie.appserver.repositories.model.User;

=======
>>>>>>> main
import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;

<<<<<<< HEAD
public class Buyer extends User {

    private final String userId;
    private String buyerName;
    public List<Bid> bidList;

    public Buyer(String buyerName) {
        this.buyerName = buyerName;
=======
public class Buyer {

    private final String userId;
    private String buyerName;
    public List<String> bidList;

    public Buyer(String sellerName) {
        this.buyerName = sellerName;
>>>>>>> main
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

<<<<<<< HEAD
    public List<Bid> getBidList() {
        return bidList;
    }

    public void setBidList(List<Bid> bidList) {
=======
    public List<String> getBidList() {
        return bidList;
    }

    public void setBidList(List<String> bidList) {
>>>>>>> main
        this.bidList = bidList;
    }
}
