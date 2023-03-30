package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleResponse {
    @JsonProperty("make")
    private String make;
    @JsonProperty("model")
    private String model;
    @JsonProperty("year")
    private String year;
    @JsonProperty("id")
    private String vehicleId;
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
