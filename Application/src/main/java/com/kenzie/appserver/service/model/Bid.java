package com.kenzie.appserver.service.model;

import static java.util.UUID.randomUUID;

public class Bid {

    private final String bidId;
    private String vehicleId;
    private Double bidPrice;

    public Bid(){
        this.bidId = randomUUID().toString();
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
