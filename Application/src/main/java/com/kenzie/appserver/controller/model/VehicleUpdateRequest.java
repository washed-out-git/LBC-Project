package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class VehicleUpdateRequest {

    @NotEmpty
    @JsonProperty("id")
    private String vehicleId;
    @NotEmpty
    @JsonProperty("make")
    private String make;
    @NotEmpty
    @JsonProperty("model")
    private String model;
    @NotEmpty
    @JsonProperty("year")
    private String year;
    @NotEmpty
    @JsonProperty("price")
    private String price;

    @NotEmpty
    @JsonProperty("sellerId")
    private String sellerId;

    public String getVehicleId() {return this.vehicleId; }

    public void setVehicleId(String vehicleId) {this.vehicleId = vehicleId; }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPrice() {return this.price; }

    public void setPrice(String price) {this.price = price; }

    public String getSellerId() {return this.sellerId; }

    public void setSellerId(String sellerId) {this.sellerId = sellerId; }
}
