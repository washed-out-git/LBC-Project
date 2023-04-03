package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class BidResponse {

    @JsonProperty("buyerId")
    private String buyerId;

    @JsonProperty("bidId")
    private String bidId;

    @JsonProperty("buyerName")
    private String buyerName;

    @JsonProperty("vehicleId")
    private String vehicleId;

    @JsonProperty("bidPrice")
    public double bidPrice;

    @JsonProperty("dateOfBid")
    public String dateOfBid;

    public String getDateOfBid() {
        return dateOfBid;
    }

    public void setDateOfBid(String dateOfBid) {
        this.dateOfBid = dateOfBid;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }


    public double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBidId() {
        return bidId;
    }

    public void setBidId(String bidId) {
        this.bidId = bidId;
    }
}
