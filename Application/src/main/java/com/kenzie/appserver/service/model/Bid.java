package com.kenzie.appserver.service.model;

import static java.util.UUID.randomUUID;

public class Bid {

    private String bidId;
    private String vehicleId;
    private Double bidPrice;

    private String dateOfBid;

    public Bid(){
        this.bidId = randomUUID().toString();
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
