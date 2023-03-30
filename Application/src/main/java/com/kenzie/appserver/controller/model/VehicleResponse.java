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
    private int year;
    @JsonProperty("isAvailable")
    private Boolean isAvailable;
    @JsonProperty("vehicleId")
    private String vehicleId;
    @JsonProperty("price")
    private int price;

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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getVehicleId() {return this.vehicleId; }

    public void setVehicleId(String vehicleId) {this.vehicleId = vehicleId; }

    public int getPrice() {return this.price; }

    public void setPrice(int price) {this.price = price; }
}
