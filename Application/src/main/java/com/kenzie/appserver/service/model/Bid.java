package com.kenzie.appserver.service.model;

import java.time.LocalDate;

import static java.util.UUID.randomUUID;

public class Bid {

    private String buyerId;
    private String bidId;
    private String vehicleId;
    private Double bidPrice;
    private String dateOfBid;

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public void setBidId(String bidId) {
        this.bidId = bidId;
    }

    public Bid(){
        this.bidId = randomUUID().toString();
        this.dateOfBid =  LocalDate.now().toString();
    }

    public String getDateOfBid() {
        return dateOfBid;
    }

    public void setDateOfBid(String dateOfBid) {
        this.dateOfBid = dateOfBid;
    }


    public String getBidId() {
        return bidId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(Double bidPrice) {
        this.bidPrice = bidPrice;
    }
}
