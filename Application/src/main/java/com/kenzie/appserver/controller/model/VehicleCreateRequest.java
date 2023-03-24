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
    private int year;
    @NotEmpty
    @JsonProperty("isAvailable")
    private boolean isAvailable;
    @NotEmpty
    @JsonProperty("vehicleId")
    private String vehicleId;

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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getVehicleId() {return this.vehicleId; }

    public void setVehicleId(String vehicleId) {this.vehicleId = vehicleId; }
}