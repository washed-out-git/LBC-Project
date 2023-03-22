package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

import static java.util.UUID.randomUUID;

public class BidCreateRequest {

    @NotEmpty
    @JsonProperty("vehicleId")
    private String vehicleId;

    @NotEmpty
    @JsonProperty("buyerId")
    private String buyerId;

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    @NotEmpty
    @JsonProperty("bidPrice")
    private Double bidPrice;

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
