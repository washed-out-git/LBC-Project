package com.kenzie.appserver.controller.model;

import com.amazonaws.services.dynamodbv2.xspec.S;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class VehicleCreateRequest {
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
    @JsonProperty("id")
    private String vehicleId;
    @NotEmpty
    @JsonProperty("price")
    private String price;

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

    public String getVehicleId() {return this.vehicleId; }

    public void setVehicleId(String vehicleId) {this.vehicleId = vehicleId; }

    public String getPrice() {return this.price; }

    public void setPrice(String price) {this.price = price; }
}
