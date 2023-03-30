package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.Bid;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class BuyerCreateRequest {

    @JsonProperty("userId")
    private String userId;

    @NotEmpty
    @JsonProperty("buyerName")
    private String buyerName;

    @JsonProperty("bidList")
    public List<Bid> bidList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
