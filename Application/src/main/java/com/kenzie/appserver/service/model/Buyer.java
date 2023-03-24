package com.kenzie.appserver.service.model;
import com.kenzie.appserver.repositories.model.User;
import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;

public class Buyer extends User {

    private String userId;
    private String buyerName;
    public List<Bid> bidList;

    public Buyer(String buyerName) {
        this.buyerName = buyerName;
        this.userId = randomUUID().toString();
        this.bidList = new ArrayList<>();
    }

}
