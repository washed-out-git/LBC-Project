package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.Bid;

import javax.validation.constraints.NotEmpty;

import java.util.List;

import static java.util.UUID.randomUUID;

public class BidCreateRequest {

    @NotEmpty
    @JsonProperty("buyerId")
    private String buyerId;

    @NotEmpty
    @JsonProperty("buyerName")
    private String buyerName;

    @NotEmpty
    @JsonProperty("vehicleId")
    private String vehicleId;

    @NotEmpty
    @JsonProperty("bidPrice")
    public double bidPrice;

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

    public void setBuyerId(String userId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

}
