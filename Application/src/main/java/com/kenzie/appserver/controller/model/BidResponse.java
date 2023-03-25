package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class BidResponse {

    @NotEmpty
    @JsonProperty("bidId")
    private String bidId;

    @NotEmpty
    @JsonProperty("vehicleId")
    private String vehicleId;

    @NotEmpty
    @JsonProperty("bidPrice")
    private Double bidPrice;

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
